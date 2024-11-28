package com.gi2.footwork.common.repository

interface PreferencesRepository {
  suspend fun getAuthToken(): String?
  suspend fun setAuthToken(token: String): Boolean
  suspend fun revokeAuthToken(): Boolean
}