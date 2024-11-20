package com.gi2.footwork.data.network.auth

import com.gi2.footwork.data.network.auth.models.LoginResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {
  @POST("login")
  suspend fun signin(
    @Field("email") email: String,
    @Field("password") password: String,
  ): ApiResponse<LoginResponse>
}