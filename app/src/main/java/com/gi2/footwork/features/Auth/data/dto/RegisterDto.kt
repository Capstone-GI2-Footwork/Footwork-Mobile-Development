package com.gi2.footwork.features.Auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto (
    val email : String,
    val password : String,
    val fullname  : String,
    val updated_at : Long
)