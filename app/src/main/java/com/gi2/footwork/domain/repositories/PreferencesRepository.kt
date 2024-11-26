package com.gi2.footwork.domain.repositories

interface PreferencesRepository {
  suspend fun getAuthToken(): String?
  suspend fun setAuthToken(token: String): Boolean
  suspend fun revokeAuthToken(): Boolean
}