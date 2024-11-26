package com.gi2.footwork.ui.viewmodel.signin

import androidx.compose.runtime.Immutable
import com.gi2.footwork.ui.common.UiStatus

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