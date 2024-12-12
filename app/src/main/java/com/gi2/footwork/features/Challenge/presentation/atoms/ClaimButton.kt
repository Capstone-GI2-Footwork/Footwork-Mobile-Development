package com.gi2.footwork.features.Challenge.presentation.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed

@Composable
fun ClaimButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Box(modifier = modifier.clickable { onClick() }) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(end = 12.dp)
        .zIndex(2f)
    ) {
      Box(
        modifier = Modifier
          .size(12.dp)
          .background(
            color = Color(0xFFFF4C31),
            shape = RoundedCornerShape(50.dp)
          )
          .align(Alignment.TopEnd)
      )
    }

    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.padding(vertical = 6.dp)
    ) {
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .background(color = primaryFixed)
          .fillMaxSize()
      ) {
        Text(
          text = "Claim",
          style = AppTypography.labelLarge.copy(
            fontWeight = FontWeight(600), color = onPrimaryFixed
          )
        )
      }
    }
  }
}
