package com.gi2.footwork.ui.composables.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.composables.atoms.*
import com.gi2.footwork.ui.composables.common.keyboardAsState
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun SignUpScreen(
  modifier: Modifier = Modifier,
  onBack: () -> Unit,
  onNavigateToSignIn: () -> Unit,
) {
  val isKeyboardOpen by keyboardAsState()

  BackHandler(enabled = !isKeyboardOpen) {
    onBack()
  }

  SignUpScreenContent(
    modifier = modifier,
    onNavigateToSignIn = onNavigateToSignIn
  )
}

@Composable
private fun SignUpScreenContent(
  modifier: Modifier = Modifier,
  onNavigateToSignIn: () -> Unit,
) {
  Scaffold(
    modifier = modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.background
          )
        )
      )
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
      Box(
        modifier = Modifier
          .fillMaxHeight(0.4f)
          .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
      ) {
        Image(
          painterResource(com.gi2.footwork.R.drawable.img_illustration_auth),
          contentDescription = null,
          modifier = Modifier
            .width(240.dp)
            .height(240.dp)
            .aspectRatio(1f)
        )
      }
      Column(
        modifier = Modifier
          .fillMaxHeight(1f)
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
          .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Spacer(modifier = Modifier.height(16.dp))
          EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            error = "",
            onValueChange = {}
          )
          Spacer(modifier = Modifier.height(16.dp))
          PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            error = "",
            onValueChange = {}
          )
          Spacer(modifier = Modifier.height(16.dp))
          BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Create Account",
            onClick = {}
          )
          Text(
            "Or",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          GoogleSignInButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
          )
          Spacer(modifier = Modifier.height(16.dp))
          InteractiveText(
            text = "Already have an account? ",
            clickableText = "Login Here",
            onClick = onNavigateToSignIn
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
  FootworkTheme {
    SignUpScreenContent(
      onNavigateToSignIn = {}
    )
  }
}