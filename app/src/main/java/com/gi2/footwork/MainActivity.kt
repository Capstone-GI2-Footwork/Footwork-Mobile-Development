package com.gi2.footwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.gi2.footwork.ui.composables.common.scopedViewModel
import com.gi2.footwork.ui.composables.screens.*
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.auth.AuthViewModel
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
            val viewModel: AuthViewModel = it.scopedViewModel(navController)

            IndexScreen(
              navController = navController,
              viewModel = viewModel
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
          composable<FootworkRoute.Home> {
            HomeScreenFallback()
          }
        }
      }
    }
  }
}

@Composable
private fun HomeScreenFallback() {
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text("Hello, User!")
    }
  }
}