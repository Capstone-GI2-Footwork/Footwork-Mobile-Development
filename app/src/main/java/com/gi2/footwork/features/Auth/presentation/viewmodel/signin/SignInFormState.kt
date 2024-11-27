package com.gi2.footwork.features.Auth.presentation.viewmodel.signin

import android.util.Patterns
import androidx.compose.runtime.Immutable

@Immutable
data class SignInFormState(
  val email: String,
  val password: String,
  val emailError: String,
  val passwordError: String,
) {
  companion object {
    fun initial() = SignInFormState(
      email = "",
      password = "",
      emailError = "",
      passwordError = ""
    )
  }

  fun validateEmail(): String {
    if (email.isEmpty()) return "Email cannot be empty."
    if (
      Patterns.EMAIL_ADDRESS
        .matcher(email)
        .matches()
    ) return "Please use a valid email address."
    return ""
  }

  fun validatePassword(): String {
    if (password.isEmpty()) return "Password cannot be empty."
    if (password.length < 8) return "Password must be at least 8 characters."
    return ""
  }

  val isValid: Boolean
    get() = validateEmail().isEmpty()
      && validatePassword().isEmpty()
}