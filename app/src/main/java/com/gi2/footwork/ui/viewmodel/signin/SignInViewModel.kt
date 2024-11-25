package com.gi2.footwork.ui.viewmodel.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gi2.footwork.domain.usecases.auth.SignInParams
import com.gi2.footwork.domain.usecases.auth.SignInUseCase
import com.gi2.footwork.ui.common.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
  private val signInUseCase: SignInUseCase,
) : ViewModel(), ContainerHost<SignInState, SignInSideEffect> {

  override val container =
    viewModelScope.container<SignInState, SignInSideEffect>(
      SignInState.initial()
    )

  fun onEmailChanged(email: String) {
    intent {
      reduce {
        state.copy(
          form = state.form.copy(
            email = email,
            emailError = state.form.validateEmail()
          )
        )
      }
    }
  }

  fun onPasswordChanged(password: String) {
    intent {
      reduce {
        state.copy(
          form = state.form.copy(
            password = password,
            passwordError = state.form.validatePassword()
          )
        )
      }
    }
  }

  fun onSubmit() {
    intent {
      if (state.form.isValid) {
        reduce { state.copy(status = UiStatus.Loading) }

        container.scope.launch {
          signInUseCase(
            SignInParams(
              email = state.form.email,
              password = state.form.password
            )
          ).fold(
            onSuccess = {
              reduce {
                state.copy(
                  status = UiStatus.Success,
                  form = SignInFormState.initial()
                )
              }
              postSideEffect(SignInSideEffect.OnSuccessNavigate)
              postSideEffect(
                SignInSideEffect.ShowMessage(
                  "Sign in successful! Redirecting..."
                )
              )
            },
            onFailure = {
              val message = it.message
                ?: "Uh oh! Cannot sign in at the moment. Please try again later."
              reduce { state.copy(status = UiStatus.Failure(message)) }
              postSideEffect(SignInSideEffect.ShowMessage(message))
            }
          )
        }
      }
    }
  }
}