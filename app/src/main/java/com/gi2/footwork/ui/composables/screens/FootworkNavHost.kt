package com.gi2.footwork.ui.composables.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*

@Composable
fun FootworkNavHost() {
  val navController = rememberNavController()

  NavHost(
    navController,
    startDestination = FootworkRoute.Index,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None }
  ) {
    addIndex(navController)
    addOnboarding(navController)
    addSignIn(navController)
    addSignUp(navController)
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

private fun NavGraphBuilder.addSignIn(navController: NavController) {
  composable<FootworkRoute.SignIn> {
    SignInScreen(
      onBack = {
        navController.navigate(FootworkRoute.Onboarding) {
          popUpTo(FootworkRoute.Onboarding) { inclusive = true }
        }
      },
    )
  }
}

private fun NavGraphBuilder.addSignUp(navController: NavController) {
  composable<FootworkRoute.SignUp> {
    SignUpScreen(
      onBack = {
        navController.navigate(FootworkRoute.Onboarding) {
          popUpTo(FootworkRoute.Onboarding) { inclusive = true }
        }
      },
    )
  }
}