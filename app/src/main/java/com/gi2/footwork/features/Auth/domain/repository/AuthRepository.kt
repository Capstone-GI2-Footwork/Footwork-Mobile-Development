package com.gi2.footwork.features.Auth.domain.repository

interface AuthRepository {
  suspend fun getAuth(): Boolean

  suspend fun signInWithEmailAndPassword(
    email: String,
    password: String,
  ): Result<Unit>

  suspend fun signUpWithEmailAndPassword(
    fullName: String,
    email: String,
    password: String,
  ): Result<Unit>

  suspend fun signOut(): Result<Unit>
}