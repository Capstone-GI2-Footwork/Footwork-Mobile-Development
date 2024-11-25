package com.gi2.footwork.ui.viewmodel.signin

sealed class SignInSideEffect {
  data object OnNavigate : SignInSideEffect()
  data class ShowMessage(val message: String) : SignInSideEffect()
}