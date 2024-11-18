package com.gi2.footwork.ui.composables.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gi2.footwork.ui.composables.common.keyboardAsState

@Composable
fun SignUpScreen(
  onBack: () -> Unit,
) {
  val isKeyboardOpen by keyboardAsState()
  BackHandler(enabled = !isKeyboardOpen) {
    onBack()
  }

  SignUpScreenContent()
}

@Composable
private fun SignUpScreenContent() {
  Scaffold(
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("Sign Up")
    }
  }
}