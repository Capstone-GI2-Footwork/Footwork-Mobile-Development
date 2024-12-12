package com.gi2.footwork.features.Auth.presentation.viewmodel.auth

import androidx.compose.runtime.Immutable
import com.gi2.footwork.common.helper.UiStatus

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