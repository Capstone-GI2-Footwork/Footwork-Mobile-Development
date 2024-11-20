package com.gi2.footwork.ui.composables.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.gi2.footwork.ui.common.UiStatus
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.viewmodel.auth.AuthState
import kotlinx.coroutines.delay

@Composable
fun IndexScreen(
  modifier: Modifier = Modifier,
  onNavigate: () -> Unit,
  state: AuthState,
) {
  LaunchedEffect(state) {
    delay(1000L)
    if (state.status !is UiStatus.Loading) {
      onNavigate()
    }
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
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth()
      )
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
          .fillMaxSize()
          .padding(bottom = 24.dp)
          .paint(
            painter = painterResource(R.drawable.img_footwork_illustration_onboarding),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.BottomCenter
          )
          .padding(24.dp)
      ) {
        Text(
          "Footwork",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.SemiBold,
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          "Welcome to Footwork",
          style = MaterialTheme.typography.bodyMedium,
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IndexScreenPreview() {
  FootworkTheme {
    IndexScreenContent()
  }
}