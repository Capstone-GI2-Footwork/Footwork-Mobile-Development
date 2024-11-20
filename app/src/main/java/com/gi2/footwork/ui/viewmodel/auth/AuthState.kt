package com.gi2.footwork.ui.viewmodel.auth

import androidx.compose.runtime.Immutable
import com.gi2.footwork.ui.common.UiStatus

@Immutable
data class AuthState(
  val status: UiStatus,
  val isAuthed: Boolean,
) {
  companion object {
    fun initial() = AuthState(
      status = UiStatus.Loading,
      isAuthed = false
    )
  }
}