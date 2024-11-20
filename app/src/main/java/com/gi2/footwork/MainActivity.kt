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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.gi2.footwork.ui.composables.common.scopedViewModel
import com.gi2.footwork.ui.composables.screens.*
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.auth.AuthSideEffect
import com.gi2.footwork.ui.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

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

private fun NavGraphBuilder.addIndex(
  navController: NavController,
) {
  composable<FootworkRoute.Index> {
    val viewModel: AuthViewModel = it.scopedViewModel(navController)
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { effect ->
      when (effect) {
        is AuthSideEffect.OnNavigate -> {
          navController.navigate(effect.route) {
            popUpTo(FootworkRoute.Index) {
              inclusive = true
            }
          }
        }
      }
    }

    IndexScreen(
      state = state,
      onNavigate = {
        viewModel.redirect(
          if (state.isAuthed) FootworkRoute.Home
          else FootworkRoute.Onboarding
        )
      },
    )
  }
}

@Composable
private fun HomeScreenFallback() {
  // TODO: later will be implemented by Juan

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