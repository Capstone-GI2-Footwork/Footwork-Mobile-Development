package com.gi2.footwork.features.Auth.domain

import com.gi2.footwork.features.Auth.domain.model.LoginResponse
import com.gi2.footwork.features.Auth.domain.model.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {
  @POST("login")
  suspend fun signin(
    @Field("email") email: String,
    @Field("password") password: String,
  ): ApiResponse<LoginResponse>

  @POST("register")
  suspend fun signup(
    @Field("email") email: String,
    @Field("password") password: String,
    @Field("fullname") name: String,
    @Field("updated_at") updatedAt: Int = System.currentTimeMillis().toInt(),
  ): ApiResponse<RegisterResponse>
}