package com.gi2.footwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.*
import com.gi2.footwork.ui.composables.screens.*
import com.gi2.footwork.ui.theme.FootworkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      val navController = rememberNavController()

      FootworkTheme {
        NavHost(
          navController,
          startDestination = FootworkRoute.Index,
          enterTransition = { EnterTransition.None },
          exitTransition = { ExitTransition.None }
        ) {
          composable<FootworkRoute.Index> {
            IndexScreen(
              navController = navController
            )
          }
          composable<FootworkRoute.Onboarding> {
            OnboardingScreen(
              navController = navController,
            )
          }
          composable<FootworkRoute.SignIn> {
            SignInScreen(
              navController = navController
            )
          }
          composable<FootworkRoute.SignUp> {
            SignUpScreen(
              navController = navController
            )
          }
        }
      }
    }
  }
}