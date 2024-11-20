package com.gi2.footwork.ui.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gi2.footwork.FootworkRoute
import com.gi2.footwork.domain.usecases.auth.GetUserUseCase
import com.gi2.footwork.ui.common.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
  private val getUserUseCase: GetUserUseCase,
) : ViewModel(), ContainerHost<AuthState, AuthSideEffect> {
  override val container = viewModelScope.container<AuthState, AuthSideEffect>(
    initialState = AuthState.initial()
  ) {
    fetchUser()
  }

  private fun fetchUser() {
    intent {
      container.scope.launch {
        getUserUseCase(Unit).fold(
          onSuccess = { isAuthed ->
            reduce {
              state.copy(
                status = UiStatus.Success,
                isAuthed = isAuthed
              )
            }
            postSideEffect(
              AuthSideEffect.OnNavigate(
                if (isAuthed) FootworkRoute.Home
                else FootworkRoute.Onboarding
              )
            )
          },
          onFailure = {
            reduce {
              state.copy(
                status = UiStatus.Failure(it.message ?: ""),
                isAuthed = false
              )
            }
            postSideEffect(AuthSideEffect.OnNavigate(FootworkRoute.Onboarding))
          }
        )
      }
    }
  }

  fun redirect(route: FootworkRoute) {
    intent {
      postSideEffect(AuthSideEffect.OnNavigate(route))
    }
  }
}