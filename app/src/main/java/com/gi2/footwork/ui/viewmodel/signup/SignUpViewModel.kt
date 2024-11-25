package com.gi2.footwork.ui.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gi2.footwork.domain.usecases.auth.SignUpParams
import com.gi2.footwork.domain.usecases.auth.SignUpUseCase
import com.gi2.footwork.ui.common.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val signUpUseCase: SignUpUseCase,
) : ViewModel(), ContainerHost<SignUpState, SignUpSideEffect> {

  override val container =
    viewModelScope.container<SignUpState, SignUpSideEffect>(
      SignUpState.initial()
    )

  fun onFullNameChanged(name: String) {
    intent {
      reduce {
        state.copy(
          form = state.form.copy(
            fullName = name,
            fullNameError = state.form.validateFullName()
          )
        )
      }
    }
  }

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
          signUpUseCase(
            SignUpParams(
              fullName = state.form.fullName,
              email = state.form.email,
              password = state.form.password
            )
          )
        }
      }
    }
  }
}