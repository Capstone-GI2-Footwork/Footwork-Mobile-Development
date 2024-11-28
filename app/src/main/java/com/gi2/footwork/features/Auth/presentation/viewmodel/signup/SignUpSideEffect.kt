package com.gi2.footwork.features.Auth.presentation.viewmodel.signup

sealed class SignUpSideEffect {
  data object OnSuccessNavigate : SignUpSideEffect()
  data class ShowMessage(val message: String) : SignUpSideEffect()
}