package com.gi2.footwork.features.Challenge.presentation.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.primaryFixed

@Composable
fun ChallengeProgressIndicator(
  modifier: Modifier = Modifier,
  progress: Float = 0.3f,
  color: Color = primaryFixed,
  trackColor: Color = Color(0xFFF4F3F3),
  clipShape: Shape = RoundedCornerShape(6.dp),
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(clipShape)
      .background(trackColor)
      .height(24.dp)
  ) {
    Box(
      modifier = Modifier
        .clip(clipShape)
        .background(color)
        .fillMaxHeight()
        .fillMaxWidth(progress)
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun ChallengeProgressIndicatorPreview() {
  FootworkTheme {
    ChallengeProgressIndicator()
  }
}
