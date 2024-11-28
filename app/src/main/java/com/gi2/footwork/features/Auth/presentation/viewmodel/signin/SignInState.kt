package com.gi2.footwork.features.Auth.presentation.viewmodel.signin

import androidx.compose.runtime.Immutable
import com.gi2.footwork.common.helper.UiStatus

@Immutable
data class SignInState(
    val status: UiStatus,
    val form: SignInFormState,
) {

  companion object {
    fun initial() = SignInState(
      status = UiStatus.Idle,
      form = SignInFormState.initial()
    )
  }
}