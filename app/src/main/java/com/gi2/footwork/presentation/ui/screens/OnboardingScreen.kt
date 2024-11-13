package com.gi2.footwork.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.presentation.ui.composables.BrandButton
import com.gi2.footwork.presentation.ui.theme.FootworkTheme

@Composable
fun OnboardingScreen(
  modifier: Modifier = Modifier,
) {
  //  TODO: Implement OnboardingViewModel
  OnboardingScreenContent(
    modifier = modifier
  )
}

@Composable
private fun OnboardingScreenContent(
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(top = 24.dp)
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .paint(
            painter = painterResource(R.drawable.img_footwork_illustration_onboarding),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter
          )
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            "Let's Get Started",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
          )
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            BrandButton(
              modifier = Modifier.fillMaxWidth(),
              text = "Sign Up",
              onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              "Already have an account?",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.outline,
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
  FootworkTheme {
    OnboardingScreenContent()
  }
}