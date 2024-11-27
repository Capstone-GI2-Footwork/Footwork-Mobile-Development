package com.gi2.footwork.features.Auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
  val isError: Boolean = false,
  val message: String?,
  val token: String,
)