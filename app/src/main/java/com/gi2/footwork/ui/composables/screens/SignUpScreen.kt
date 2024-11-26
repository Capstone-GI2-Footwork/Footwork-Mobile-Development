package com.gi2.footwork.ui.composables.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.common.UiStatus
import com.gi2.footwork.ui.composables.atoms.*
import com.gi2.footwork.ui.composables.common.keyboardAsState
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.signup.SignUpState

@Composable
fun SignUpScreen(
  modifier: Modifier = Modifier,
  state: SignUpState,
  onSubmit: () -> Unit,
  onNameChange: (String) -> Unit,
  onEmailChange: (String) -> Unit,
  onPasswordChange: (String) -> Unit,
  onBack: () -> Unit,
  onNavigateToSignIn: () -> Unit,
) {
  val isKeyboardOpen by keyboardAsState()

  BackHandler(enabled = !isKeyboardOpen) {
    onBack()
  }

  SignUpScreenContent(
    modifier = modifier,
    onNavigateToSignIn = onNavigateToSignIn,
    state = state,
    onNameChanged = onNameChange,
    onEmailChanged = onEmailChange,
    onPasswordChanged = onPasswordChange,
    onSubmit = onSubmit
  )
}

@Composable
private fun SignUpScreenContent(
  modifier: Modifier = Modifier,
  onNavigateToSignIn: () -> Unit,
  state: SignUpState,
  onNameChanged: (String) -> Unit,
  onEmailChanged: (String) -> Unit,
  onPasswordChanged: (String) -> Unit,
  onSubmit: () -> Unit,
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
          NameTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.status !is UiStatus.Loading,
            value = state.form.fullName,
            error = state.form.fullNameError,
            onValueChange = onNameChanged
          )
          Spacer(modifier = Modifier.height(16.dp))
          EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.status !is UiStatus.Loading,
            value = state.form.email,
            error = state.form.emailError,
            onValueChange = onEmailChanged
          )
          Spacer(modifier = Modifier.height(16.dp))
          PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = state.status !is UiStatus.Loading,
            value = state.form.password,
            error = state.form.passwordError,
            onValueChange = onPasswordChanged
          )
          Spacer(modifier = Modifier.height(16.dp))
          BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Create Account",
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
            text = "Already have an account? ",
            clickableText = "Login Here",
            enabled = state.status !is UiStatus.Loading,
            onClick = onNavigateToSignIn
          )
        }
      }
    }
  }
}

@Composable
private fun NameTextField(
  modifier: Modifier = Modifier,
  value: String,
  error: String,
  enabled: Boolean = true,
  onValueChange: (String) -> Unit,
  onDone: () -> Unit = {},
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  Column(
    modifier = Modifier.fillMaxWidth(),
  ) {
    OutlinedTextField(
      modifier = modifier,
      value = value,
      onValueChange = onValueChange,
      enabled = enabled,
      label = { Text("Full Name") },
      placeholder = { Text("Enter your full name.") },
      isError = error.isNotEmpty(),
      maxLines = 1,
      singleLine = true,
      shape = MaterialTheme.shapes.small,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          keyboardController?.hide()
          onDone()
        }
      ),
    )
    if (error.isNotEmpty()) {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = error,
        style = MaterialTheme.typography
          .bodySmall
          .copy(color = MaterialTheme.colorScheme.error),
        modifier = Modifier.padding(start = 16.dp)
      )
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
  FootworkTheme {
    SignUpScreenContent(
      onNavigateToSignIn = {},
      state = SignUpState.initial(),
      onNameChanged = {},
      onEmailChanged = {},
      onPasswordChanged = {},
      onSubmit = {}
    )
  }
}