package com.gi2.footwork.features.Auth.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun EmissionResultScreen(
  modifier: Modifier = Modifier,
  onNext: () -> Unit,
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
        "Great You have finished filling in Your vehicle data",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        "Use the app and help us to create a better environment.",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
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
      ) {}
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmissionResultScreenPreview() {
  FootworkTheme {
    EmissionResultScreen {}
  }
}
