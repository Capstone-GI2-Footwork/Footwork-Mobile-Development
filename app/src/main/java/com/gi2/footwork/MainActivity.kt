package com.gi2.footwork

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gi2.footwork.common.di.authDataStore
import com.gi2.footwork.common.navigation.FootworkRoute
import com.gi2.footwork.common.preference.PreferencesKeys
import com.gi2.footwork.common.repository.PreferencesRepository
import com.gi2.footwork.features.Auth.presentation.ui.screens.OnboardingScreen
import com.gi2.footwork.features.Auth.presentation.ui.screens.SignInScreen
import com.gi2.footwork.features.Auth.presentation.ui.screens.SignUpScreen
import com.gi2.footwork.features.Auth.presentation.viewmodel.auth.AuthSideEffect
import com.gi2.footwork.features.Auth.presentation.viewmodel.auth.AuthViewModel
import com.gi2.footwork.features.Auth.presentation.viewmodel.signin.SignInSideEffect
import com.gi2.footwork.features.Auth.presentation.viewmodel.signin.SignInViewModel
import com.gi2.footwork.features.Auth.presentation.viewmodel.signup.SignUpSideEffect
import com.gi2.footwork.features.Auth.presentation.viewmodel.signup.SignUpViewModel
import com.gi2.footwork.features.Home.presentation.screen.HomeScreenContent
import com.gi2.footwork.ui.composables.common.scopedViewModel
import com.gi2.footwork.ui.theme.FootworkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
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
            val token = remember{
                mutableStateOf(
                    runBlocking {
                        preferencesRepository.getAuthToken()
                    }
                )
            }
            val navController = rememberNavController()
            FootworkTheme {
                NavHost(
                    navController,
                    startDestination =
                        if (token.value != null){
                            FootworkRoute.Index
                        }else{
                            FootworkRoute.Onboarding
                    },
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

//        viewModel.collectSideEffect { effect ->
//            when (effect) {
//                is AuthSideEffect.OnNavigate -> {
//                    navController.navigate(effect.route) {
//                        popUpTo(FootworkRoute.Index) {
//                            inclusive = true
//                        }
//                    }
//                }
//            }
//        }

        HomeScreenContent(
//      state = state,
//      onNavigate = {
//        viewModel.redirect(
//          if (state.isAuthed) FootworkRoute.Home
//          else FootworkRoute.Onboarding
//        )
//      },
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
