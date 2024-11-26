package com.gi2.footwork.ui.common

sealed class UiStatus() {
  data object Idle : UiStatus()
  data object Loading : UiStatus()
  data object Success : UiStatus()
  data class Failure(val message: String) : UiStatus()
}