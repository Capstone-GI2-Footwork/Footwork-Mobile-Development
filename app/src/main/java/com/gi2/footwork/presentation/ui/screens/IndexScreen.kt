package com.gi2.footwork.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun IndexScreen(
  modifier: Modifier = Modifier,
  onNavigateToNextScreen: () -> Unit,
) {
  /**
   * TODO: Implement IndexViewModel
   * IndexViewModel should be able to check the user's auth status.
   * Meanwhile, this screen will render loader and redirect to the next screen
   */

  LaunchedEffect(Unit) {
    delay(3000L) // simulate user credential fetching
    onNavigateToNextScreen()
  }

  IndexScreenContent(
    modifier = modifier
  )
}

@Composable
private fun IndexScreenContent(
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    ) {
      CircularProgressIndicator()
    }
  }
}