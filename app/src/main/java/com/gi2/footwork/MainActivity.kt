package com.gi2.footwork

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gi2.footwork.common.navigation.FootworkRoute
import com.gi2.footwork.common.repository.PreferencesRepository
import com.gi2.footwork.features.Auth.presentation.ui.screens.IntroScreen
import com.gi2.footwork.features.Auth.presentation.ui.screens.OnboardingScreen
import com.gi2.footwork.features.Auth.presentation.ui.screens.SignInScreen
import com.gi2.footwork.features.Auth.presentation.ui.screens.SignUpScreen
import com.gi2.footwork.features.Auth.presentation.viewmodel.auth.AuthViewModel
import com.gi2.footwork.features.Auth.presentation.viewmodel.signin.SignInSideEffect
import com.gi2.footwork.features.Auth.presentation.viewmodel.signin.SignInViewModel
import com.gi2.footwork.features.Auth.presentation.viewmodel.signup.SignUpSideEffect
import com.gi2.footwork.features.Auth.presentation.viewmodel.signup.SignUpViewModel
import com.gi2.footwork.features.Home.presentation.screen.HomeScreenContent
import com.gi2.footwork.ui.composables.common.scopedViewModel
import com.gi2.footwork.ui.theme.FootworkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  lateinit var preferencesRepository: PreferencesRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      val navController = rememberNavController()

      val token by remember {
        mutableStateOf(
          runBlocking {
            preferencesRepository.getAuthToken()
          }
        )
      }

      val isIntroCompleted by remember {
        mutableStateOf(
          runBlocking {
            preferencesRepository.isIntroCompleted()
          }
        )
      }

      val startDestination = if (token != null) {
        if (isIntroCompleted) {
          FootworkRoute.Index
        } else {
          FootworkRoute.Intro
        }
      } else {
        FootworkRoute.Onboarding
      }

      FootworkTheme {
        NavHost(
          navController,
          startDestination = startDestination,
          enterTransition = { EnterTransition.None },
          exitTransition = { ExitTransition.None }
        ) {
          addIndex(navController)
          addIntro(
            navController,
          ) {
            runBlocking {
              preferencesRepository.setIntroCompleted()
            }
          }
          addOnboarding(navController)
          addSignIn(navController, isIntroCompleted)
          addSignUp(navController)
        }
      }
    }
  }
}

@Suppress("UNUSED_PARAMETER")
private fun NavGraphBuilder.addIndex(navController: NavController) {
  composable<FootworkRoute.Index> {
    HomeScreenContent()
  }
}

private fun NavGraphBuilder.addIntro(
  navController: NavController,
  setIntroCompleted: () -> Unit,
) {
  composable<FootworkRoute.Intro> {
    IntroScreen(
      onFormSubmit = {},
      onResultNext = {
        setIntroCompleted()
        navController.navigate(FootworkRoute.Index) {
          popUpTo(FootworkRoute.Index) { inclusive = true }
        }
      }
    )
  }
}

private fun NavGraphBuilder.addOnboarding(navController: NavController) {
  composable<FootworkRoute.Onboarding> {
    OnboardingScreen(
      onRedirectSignUp = {
        navController.navigate(FootworkRoute.SignUp)
      },
      onRedirectSignIn = {
        navController.navigate(FootworkRoute.SignIn)
      },
    )
  }
}

private fun NavGraphBuilder.addSignIn(
  navController: NavController,
  isIntroCompleted: Boolean,
) {
  composable<FootworkRoute.SignIn> {
    val viewModel = it.scopedViewModel<SignInViewModel>(navController)
    val authViewModel = it.scopedViewModel<AuthViewModel>(navController)

    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { effect ->
      when (effect) {
        SignInSideEffect.OnSuccessNavigate -> {
          val route = if (isIntroCompleted) {
            FootworkRoute.Index
          } else {
            FootworkRoute.Intro
          }

          navController.navigate(route) {
            popUpTo(route) { inclusive = true }
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

private fun NavGraphBuilder.addSignUp(navController: NavController) {
  composable<FootworkRoute.SignUp> {
    val context = LocalContext.current

    val viewModel = it.scopedViewModel<SignUpViewModel>(navController)
    val state by viewModel.collectAsState()

    val authViewModel = it.scopedViewModel<AuthViewModel>(navController)

    viewModel.collectSideEffect { effect ->
      when (effect) {
        SignUpSideEffect.OnSuccessNavigate -> {
          navController.navigate(FootworkRoute.Intro) {
            popUpTo(FootworkRoute.Intro) { inclusive = true }
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
