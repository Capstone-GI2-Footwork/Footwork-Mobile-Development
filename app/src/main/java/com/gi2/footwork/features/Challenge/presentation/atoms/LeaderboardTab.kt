package com.gi2.footwork.features.Challenge.presentation.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed

@Composable
fun LeaderboardTab(
  modifier: Modifier = Modifier,
  currentIndex: Int = 0,
  onTabSelected: (Int) -> Unit,
) {
  val tabItems = listOf("Weekly", "Daily Task")

  Box(
    modifier = modifier
      .background(
        Color(0xFF469836).copy(alpha = 0.8f),
        shape = MaterialTheme.shapes.medium
      )
      .clip(MaterialTheme.shapes.medium)
      .padding(8.dp)
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
  ) {
    Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      tabItems.forEachIndexed { index, s ->
        LeaderboardTabItem(
          modifier = Modifier.weight(1f),
          text = s,
          isActive = index == currentIndex,
          onClick = { onTabSelected(index) }
        )
      }
    }
  }
}

@Composable
fun LeaderboardTabItem(
  text: String,
  modifier: Modifier = Modifier,
  isActive: Boolean = false,
  onClick: () -> Unit = {},
) {
  Box(
    modifier = modifier
      .fillMaxWidth(1f)
      .fillMaxHeight(1f)
      .background(
        color = if (isActive) primaryFixed else Color.Transparent,
        shape = MaterialTheme.shapes.small
      )
      .clip(shape = MaterialTheme.shapes.small)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clickable { onClick() },
    contentAlignment = Alignment.Center
  ) {
    Text(
      text,
      style = MaterialTheme.typography.titleMedium,
      color = onPrimaryFixed.copy(
        alpha = if (isActive) 1f else 0.6f
      ),
      fontWeight = FontWeight.SemiBold
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun LeaderboardTabPreview() {
  var currentIndex by remember { mutableIntStateOf(0) }

  FootworkTheme {
    Box(
      modifier = Modifier.padding(16.dp)
    ) {
      LeaderboardTab(
        currentIndex = currentIndex,
        onTabSelected = { index -> currentIndex = index }
      )
    }
  }
}
