package com.gi2.footwork.presentation.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.gi2.footwork.presentation.ui.screens.IndexScreen
import com.gi2.footwork.presentation.ui.screens.OnboardingScreen
import kotlinx.serialization.Serializable

@Composable
fun FootworkNavigation() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = FootworkRoute.IndexPage
  ) {
    composable<FootworkRoute.IndexPage> {
      IndexScreen(
        onNavigateToNextScreen = {
          navController.navigate(FootworkRoute.OnboardingPage) {
            popUpTo(navController.graph.id) {
              inclusive = true
            }
          }
        }
      )
    }

    composable<FootworkRoute.OnboardingPage> {
      OnboardingScreen()
    }
  }
}

@Serializable
sealed class FootworkRoute {
  @Serializable
  data object IndexPage : FootworkRoute()

  @Serializable
  data object OnboardingPage : FootworkRoute()
}