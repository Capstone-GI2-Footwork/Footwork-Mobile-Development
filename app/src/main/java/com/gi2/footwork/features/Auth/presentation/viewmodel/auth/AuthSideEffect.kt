package com.gi2.footwork.features.Auth.presentation.viewmodel.auth

import com.gi2.footwork.common.navigation.FootworkRoute

sealed class AuthSideEffect {
  data class OnNavigate(val route: FootworkRoute) : AuthSideEffect()
}