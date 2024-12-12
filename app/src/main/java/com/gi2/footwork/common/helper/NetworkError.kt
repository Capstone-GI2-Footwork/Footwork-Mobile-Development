package com.gi2.footwork.common.helper

sealed class NetworkError(
  override val message: String,
  @Suppress("unused") private val code: Int = 500,
) : Throwable() {

  data class BadRequest(
    override val message: String = "There was an issue with your request. Please review your data and try again.",
  ) : NetworkError(message, code = 400)

  data class InternalServer(
    override val message: String = "Sorry, something went wrong. We’re fixing it—please try again soon.",
  ) : NetworkError(message, code = 500)
}