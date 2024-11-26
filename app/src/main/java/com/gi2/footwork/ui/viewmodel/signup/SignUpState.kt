package com.gi2.footwork.ui.viewmodel.signup

import androidx.compose.runtime.Immutable
import com.gi2.footwork.ui.common.UiStatus

@Immutable
data class SignUpState(
  val status: UiStatus,
  val form: SignUpFormState,
) {
  companion object {
    fun initial() = SignUpState(
      status = UiStatus.Idle,
      form = SignUpFormState.initial()
    )
  }
}