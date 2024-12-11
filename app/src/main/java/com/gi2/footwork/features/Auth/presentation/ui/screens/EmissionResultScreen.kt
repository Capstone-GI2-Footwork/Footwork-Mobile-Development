package com.gi2.footwork.features.Auth.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.composables.atoms.BrandButton
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.FootworkTheme

@Composable
fun EmissionResultScreen(
  modifier: Modifier = Modifier,
  onNext: () -> Unit,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      Column(
        modifier = modifier
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
          style = MaterialTheme.typography.bodyLarge,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(horizontal = 24.dp),
          color = MaterialTheme.colorScheme.onSurfaceVariant
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
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(color = Color.Black.copy(alpha = 0.4f))
          .padding(24.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(24.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            "Emission Prediction",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
          )
          Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
          ) {
            CircularProgressIndicator(
              progress = { 0.6f },
              color = Color(0xFFFFB20D),
              trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
              modifier = Modifier
                .width(180.dp)
                .height(180.dp),
              strokeWidth = 16.dp,
              gapSize = 0.dp,
              strokeCap = StrokeCap.Butt
            )
            Text(
              text = buildAnnotatedString {
                append("250 co")
                withStyle(
                  style = SpanStyle(
                    baselineShift = BaselineShift.Superscript,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize * 0.75
                  )
                ) { append("2") }
                append("g")
              },
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFB20D),
              ),
              textAlign = TextAlign.Center,
            )
          }
          Text(
            "Let's reduce carbon emission with us.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 32.dp)
          )
          BrandButton(
            text = "Next",
            onClick = onNext,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
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
