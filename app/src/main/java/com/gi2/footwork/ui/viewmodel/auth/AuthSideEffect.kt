package com.gi2.footwork.ui.viewmodel.auth

import com.gi2.footwork.FootworkRoute

sealed class AuthSideEffect {
  data class OnNavigate(val route: FootworkRoute) : AuthSideEffect()
}