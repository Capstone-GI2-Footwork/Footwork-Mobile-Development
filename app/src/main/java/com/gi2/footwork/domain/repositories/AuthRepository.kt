package com.gi2.footwork.domain.repositories

interface AuthRepository {
  suspend fun getAuth(): Boolean

  suspend fun signInWithEmailAndPassword(
    email: String,
    password: String,
  ): Result<Unit>

  suspend fun signOut(): Result<Unit>
}