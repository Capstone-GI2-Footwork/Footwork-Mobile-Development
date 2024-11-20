package com.gi2.footwork.data.network.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
  val isError: Boolean = false,
  val message: String?,
  val token: String,
)
