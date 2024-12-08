package com.gi2.footwork.features.Auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto (
    val email : String,
    val password : String,
)