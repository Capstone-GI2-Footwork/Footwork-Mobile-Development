package com.gi2.footwork.ui.composables.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gi2.footwork.FootworkRoute
import com.gi2.footwork.ui.composables.common.keyboardAsState

@Composable
fun SignInScreen(
  modifier: Modifier = Modifier,
  navController: NavController,
) {
  val isKeyboardOpen by keyboardAsState()

  BackHandler(enabled = !isKeyboardOpen) {
    navController.navigate(FootworkRoute.Onboarding) {
      popUpTo(FootworkRoute.Onboarding) { inclusive = true }
    }
  }

  SignInScreenContent(
    modifier = modifier
  )
}

@Composable
private fun SignInScreenContent(
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("Sign In")
    }
  }
}