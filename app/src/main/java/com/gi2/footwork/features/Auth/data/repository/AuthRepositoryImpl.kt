package com.gi2.footwork.features.Auth.data.repository

import com.gi2.footwork.features.Auth.data.network.AuthService
import com.gi2.footwork.common.helper.NetworkError
import com.gi2.footwork.features.Auth.domain.repository.AuthRepository
import com.gi2.footwork.common.repository.PreferencesRepository
import com.gi2.footwork.features.Auth.data.dto.RegisterDto
import com.skydoves.sandwich.suspendOnSuccess
import java.util.Calendar
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val prefsRepo: PreferencesRepository,
  private val authService: AuthService,
) : AuthRepository {
  override suspend fun getAuth(): Boolean {
    val token = prefsRepo.getAuthToken()
    return token == null
  }

  override suspend fun signInWithEmailAndPassword(
    email: String,
    password: String,
  ): Result<Unit> {
    var res: Result<Unit> = Result.failure(NetworkError.InternalServer())

    authService.signin(email, password)
      .suspendOnSuccess {
        res = if (data.isError) {
          Result.failure(
            NetworkError.BadRequest(
              data.message ?: "Invalid email or password. Try again."
            )
          )
        } else {
          prefsRepo.setAuthToken(data.token)
          Result.success(Unit)
        }
      }

    return res
  }

  override suspend fun signUpWithEmailAndPassword(
    fullName: String,
    email: String,
    password: String,
  ): Result<Unit> {
    var res: Result<Unit> = Result.failure(NetworkError.InternalServer())


    authService.signup(RegisterDto(
      email,
      password,
      fullName,
      Calendar.getInstance().timeInMillis
    ))
      .suspendOnSuccess {
        res = if (data.isError) {
          Result.failure(
            NetworkError.BadRequest(
              data.message ?: "Looks like this email is already taken."
            )
          )
        } else {
          prefsRepo.setAuthToken(data.token)
          Result.success(Unit)
        }
      }

    return res
  }

  override suspend fun signOut(): Result<Unit> {
    prefsRepo.revokeAuthToken()
    return Result.success(Unit)
  }
}