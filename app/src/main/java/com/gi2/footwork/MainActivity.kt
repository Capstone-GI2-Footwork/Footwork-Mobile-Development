package com.gi2.footwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
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
          addIndex(navController)
          addOnboarding(navController)
        }
      }
    }
  }
}

private fun NavGraphBuilder.addIndex(navController: NavController) {
  composable<FootworkRoute.Index> {
    IndexScreen(
      onNavigateToNextScreen = {
        navController.navigate(FootworkRoute.Onboarding) {
          popUpTo(FootworkRoute.Onboarding) {
            inclusive = true
          }
        }
      }
    )
  }
}

private fun NavGraphBuilder.addOnboarding(navController: NavController) {
  composable<FootworkRoute.Onboarding> {
    OnboardingScreen(
      onSignIn = {
        navController.navigate(FootworkRoute.SignIn)
      },
      onSignUp = {
        navController.navigate(FootworkRoute.SignUp)
      }
    )
  }
}