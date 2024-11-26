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
import com.gi2.footwork.R
import com.gi2.footwork.ui.common.UiStatus
import com.gi2.footwork.ui.composables.atoms.*
import com.gi2.footwork.ui.composables.common.keyboardAsState
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.signin.SignInState

@Composable
fun SignInScreen(
  modifier: Modifier = Modifier,
  state: SignInState,
  onBack: () -> Unit,
  onNavigateToSignUp: () -> Unit,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onSubmit: () -> Unit,
) {
  val isKeyboardOpen by keyboardAsState()

  BackHandler(enabled = !isKeyboardOpen) {
    onBack()
  }

  SignInScreenContent(
    modifier = modifier,
    onNavigateToSignUp = onNavigateToSignUp,
    onEmailChange = onEmailChange,
    onPasswordChange = onPasswordChange,
    onSubmit = onSubmit,
    state = state
  )
}

@Composable
private fun SignInScreenContent(
  modifier: Modifier = Modifier,
  onNavigateToSignUp: () -> Unit,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onSubmit: () -> Unit,
  state: SignInState,
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
          painterResource(R.drawable.img_illustration_auth),
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
          EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.status !is UiStatus.Loading,
            value = state.form.email,
            error = state.form.emailError,
            onValueChange = onEmailChange
          )
          Spacer(modifier = Modifier.height(16.dp))
          PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.status !is UiStatus.Loading,
            value = state.form.password,
            error = state.form.passwordError,
            onValueChange = onPasswordChange,
            onDone = onSubmit
          )
          Spacer(modifier = Modifier.height(4.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            Text(
              "Forgot password?",
              modifier = Modifier.clickable(enabled = false) { },
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.outline
            )
          }
          Spacer(modifier = Modifier.height(16.dp))
          BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Sign In",
            enabled = state.status !is UiStatus.Loading,
            onClick = onSubmit
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
            text = "Don't have an account? ",
            clickableText = "Sign up Here",
            enabled = state.status !is UiStatus.Loading,
            onClick = onNavigateToSignUp,
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignInScreenPreview() {
  FootworkTheme {
    SignInScreenContent(
      onNavigateToSignUp = {},
      onEmailChange = {},
      onPasswordChange = {},
      onSubmit = {},
      state = SignInState.initial()
    )
  }
}