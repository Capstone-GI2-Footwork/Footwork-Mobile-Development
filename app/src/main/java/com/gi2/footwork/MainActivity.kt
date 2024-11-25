package com.gi2.footwork

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.gi2.footwork.ui.composables.common.scopedViewModel
import com.gi2.footwork.ui.composables.screens.*
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.auth.AuthSideEffect
import com.gi2.footwork.ui.viewmodel.auth.AuthViewModel
import com.gi2.footwork.ui.viewmodel.signin.SignInSideEffect
import com.gi2.footwork.ui.viewmodel.signin.SignInViewModel
import com.gi2.footwork.ui.viewmodel.signup.SignUpSideEffect
import com.gi2.footwork.ui.viewmodel.signup.SignUpViewModel
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
          addSignIn(navController)
          addSignUp(navController)
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

private fun NavGraphBuilder.addSignIn(
  navController: NavController,
) {
  composable<FootworkRoute.SignIn> {
    val viewModel = it.scopedViewModel<SignInViewModel>(navController)
    val authViewModel = it.scopedViewModel<AuthViewModel>(navController)

    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { effect ->
      when (effect) {
        SignInSideEffect.OnSuccessNavigate -> {
          navController.navigate(FootworkRoute.Home) {
            popUpTo(FootworkRoute.Home) { inclusive = true }
          }
        }

        is SignInSideEffect.ShowMessage -> {
          Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
        }
      }
    }

    SignInScreen(
      state = state,
      onBack = {
        navController.navigate(FootworkRoute.Onboarding) {
          popUpTo(FootworkRoute.Onboarding) { inclusive = true }
        }
      },
      onNavigateToSignUp = {
        navController.navigate(FootworkRoute.SignUp)
      },
      onEmailChange = viewModel::onEmailChanged,
      onPasswordChange = viewModel::onPasswordChanged,
      onSubmit = {
        viewModel.onSubmit()
        authViewModel.update()
      },
    )
  }
}

private fun NavGraphBuilder.addSignUp(
  navController: NavController,
) {
  composable<FootworkRoute.SignUp> {
    val viewModel = it.scopedViewModel<SignUpViewModel>(navController)
    val authViewModel = it.scopedViewModel<AuthViewModel>(navController)

    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { effect ->
      when (effect) {
        SignUpSideEffect.OnSuccessNavigate -> {
          navController.navigate(FootworkRoute.Home) {
            popUpTo(FootworkRoute.Home) { inclusive = true }
          }
        }

        is SignUpSideEffect.ShowMessage -> {
          Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
        }
      }
    }

    SignUpScreen(
      state = state,
      onBack = {
        navController.navigate(FootworkRoute.Onboarding) {
          popUpTo(FootworkRoute.Onboarding) { inclusive = true }
        }
      },
      onNavigateToSignIn = {
        navController.navigate(FootworkRoute.SignIn)
      },
      onEmailChange = viewModel::onEmailChanged,
      onPasswordChange = viewModel::onPasswordChanged,
      onNameChange = viewModel::onFullNameChanged,
      onSubmit = {
        viewModel.onSubmit()
        authViewModel.update()
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