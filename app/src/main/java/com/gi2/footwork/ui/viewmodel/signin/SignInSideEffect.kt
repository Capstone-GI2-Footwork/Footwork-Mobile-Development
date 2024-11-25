package com.gi2.footwork.ui.viewmodel.signin

sealed class SignInSideEffect {
  data object OnSuccessNavigate : SignInSideEffect()
  data class ShowMessage(val message: String) : SignInSideEffect()
}