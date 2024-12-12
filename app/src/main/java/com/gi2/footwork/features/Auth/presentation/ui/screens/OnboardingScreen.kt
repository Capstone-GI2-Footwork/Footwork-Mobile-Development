package com.gi2.footwork.features.Auth.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.composables.atoms.BrandButton
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun OnboardingScreen(
  modifier: Modifier = Modifier,
  onRedirectSignUp: () -> Unit,
  onRedirectSignIn: () -> Unit,
) {
  OnboardingScreenContent(
    modifier = modifier,
    onSignUp = onRedirectSignUp,
    onSignIn = onRedirectSignIn
  )
}

@Composable
private fun OnboardingScreenContent(
  modifier: Modifier = Modifier,
  onSignUp: () -> Unit,
  onSignIn: () -> Unit,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(32.dp))
      Text(
        "Let's Get Started",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .paint(
            painter = painterResource(R.drawable.img_illustration_onboarding),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter
          )
          .weight(1f)
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Bottom
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            BrandButton(
              modifier = Modifier.fillMaxWidth(),
              text = "Sign Up",
              onClick = onSignUp
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigateToSignIn(onNavigate = onSignIn)
          }
        }
      }
    }
  }
}

@Composable
private fun NavigateToSignIn(
  onNavigate: () -> Unit,
) {
  val annotatedString = buildAnnotatedString {
    withStyle(
      style = MaterialTheme.typography
        .bodySmall
        .copy(MaterialTheme.colorScheme.onSurfaceVariant)
        .toSpanStyle()
    ) {
      append("Already have an account? ")
    }
    withStyle(
      style = MaterialTheme.typography
        .bodySmall
        .copy(
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.W500,
        )
        .toSpanStyle()
    ) {
      append("Login Here")
      addStringAnnotation(
        tag = "SignIn",
        start = 0,
        end = 10,
        annotation = "SignIn"
      )
    }
  }

  @Suppress("DEPRECATION")
  ClickableText(
    annotatedString,
    style = MaterialTheme.typography.bodySmall,
    onClick = {
      annotatedString.getStringAnnotations("SignIn", 0, 10)
        .firstOrNull()
        ?.let { onNavigate() }
    }
  )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
  FootworkTheme {
    OnboardingScreenContent(
      onSignUp = {},
      onSignIn = {}
    )
  }
}
