package com.gi2.footwork.features.Auth.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gi2.footwork.common.helper.UiStatus
import com.gi2.footwork.features.Auth.domain.use_case.GetUserUseCase
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
  ) { fetchUser() }

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
          },
          onFailure = {
            reduce {
              state.copy(
                status = UiStatus.Failure(it.message ?: ""),
                isAuthed = false
              )
            }
          }
        )
      }
    }
  }

  fun update() {
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
          },
          onFailure = {
            reduce {
              state.copy(
                status = UiStatus.Failure(it.message ?: ""),
                isAuthed = false
              )
            }
          }
        )
      }
    }
  }
}
