package com.gi2.footwork.common.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
  val AUTH_TOKEN_PREFERENCES_KEY = stringPreferencesKey("__auth-token")

  val INTRO_COMPLETED_PREFERENCES_KEY = booleanPreferencesKey("is_intro_completed")
}
