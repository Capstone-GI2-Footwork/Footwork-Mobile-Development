package com.gi2.footwork.data.repositories

import com.gi2.footwork.data.network.auth.AuthService
import com.gi2.footwork.domain.entities.errors.NetworkError
import com.gi2.footwork.domain.repositories.AuthRepository
import com.gi2.footwork.domain.repositories.PreferencesRepository
import com.skydoves.sandwich.suspendOnSuccess
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

  override suspend fun signOut(): Result<Unit> {
    prefsRepo.revokeAuthToken()
    return Result.success(Unit)
  }
}