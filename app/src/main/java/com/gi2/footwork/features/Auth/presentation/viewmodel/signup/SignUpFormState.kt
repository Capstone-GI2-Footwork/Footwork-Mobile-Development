package com.gi2.footwork.features.Auth.presentation.viewmodel.signup

import android.util.Patterns
import androidx.compose.runtime.Immutable

@Immutable
data class SignUpFormState(
  val fullName: String,
  val email: String,
  val password: String,
  val fullNameError: String,
  val emailError: String,
  val passwordError: String,
) {
  companion object {
    fun initial() = SignUpFormState(
      fullName = "",
      email = "",
      password = "",
      fullNameError = "",
      emailError = "",
      passwordError = ""
    )
  }

  fun validateFullName(): String {
    if (fullName.isEmpty()) return "Full name cannot be empty."
    if (fullName.length < 3) return "Full name must be at least 3 characters."
    return ""
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
      && validateFullName().isEmpty()
}
