package com.gi2.footwork.features.Auth.data.network

import com.gi2.footwork.features.Auth.data.dto.RegisterDto
import com.gi2.footwork.features.Auth.domain.model.LoginResponse
import com.gi2.footwork.features.Auth.domain.model.RegisterResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {
  @POST("api/login")
  suspend fun signin(
    @Field("email") email: String,
    @Field("password") password: String,
  ): ApiResponse<LoginResponse>

  @POST("api/register")
  suspend fun signup(
    @Body body : RegisterDto
    ,
  ): ApiResponse<RegisterResponse>
}
//
//("email") email: String,
//@Field("password") password: String,
//@Field("fullname") name: String,
//@Field("updated_at") updatedAt: Long = Calendar.getInstance().timeInMillis