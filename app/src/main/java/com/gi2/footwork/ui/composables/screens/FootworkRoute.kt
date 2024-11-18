package com.gi2.footwork.ui.composables.screens

import kotlinx.serialization.Serializable

@Serializable
sealed class FootworkRoute {

  @Serializable
  data object Index : FootworkRoute()

  @Serializable
  data object Onboarding : FootworkRoute()

  @Serializable
  data object SignIn : FootworkRoute()

  @Serializable
  data object SignUp : FootworkRoute()
}