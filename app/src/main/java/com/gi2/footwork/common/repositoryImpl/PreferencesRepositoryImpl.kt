package com.gi2.footwork.common.repositoryImpl

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.*
import com.gi2.footwork.common.preference.PreferencesKeys
import com.gi2.footwork.common.repository.PreferencesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
  private val authDataStore: DataStore<Preferences>,
) : PreferencesRepository {
  override suspend fun getAuthToken(): String? {
    return authDataStore.data
      .catch { ex ->
        if (ex is IOException) emit(emptyPreferences())
        else throw ex
      }
      .map { it[PreferencesKeys.AUTH_TOKEN_PREFERENCES_KEY] }
      .first()
  }

  override suspend fun setAuthToken(token: String): Boolean {
    return try {
      authDataStore.edit {
        it[PreferencesKeys.AUTH_TOKEN_PREFERENCES_KEY] = token
      }
      true
    } catch (e: Exception) {
      false
    }
  }

  override suspend fun revokeAuthToken(): Boolean {
    return try {
      authDataStore.edit {
        it.remove(PreferencesKeys.AUTH_TOKEN_PREFERENCES_KEY)
      }
      true
    } catch (e: Exception) {
      false
    }
  }
}