package com.gi2.footwork.ui.viewmodel.signup

sealed class SignUpSideEffect {
  data object OnSuccessNavigate : SignUpSideEffect()
  data class ShowMessage(val message: String) : SignUpSideEffect()
}