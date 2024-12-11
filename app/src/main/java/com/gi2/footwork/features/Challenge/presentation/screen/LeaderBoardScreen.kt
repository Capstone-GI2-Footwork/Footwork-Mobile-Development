package com.gi2.footwork.features.Challenge.presentation.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gi2.footwork.features.Challenge.domain.model.LeaderboardUser
import com.gi2.footwork.features.Challenge.presentation.atoms.LeaderboardRank
import com.gi2.footwork.features.Challenge.presentation.atoms.LeaderboardUserListItem
import com.gi2.footwork.ui.composables.common.drawRipple
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import kotlin.random.Random

@Composable
fun LeaderBoardScreen(
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = primaryFixed)
        .drawRipple()
        .padding(top = innerPadding.calculateTopPadding()),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        "Leaderboard",
        style = MaterialTheme.typography.titleMedium,
        color = onPrimaryFixed,
        fontWeight = FontWeight.SemiBold
      )
      Spacer(modifier = Modifier.height(16.dp))
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.Bottom
        ) {
          LeaderboardRank(
            rank = 2,
            user = LeaderboardUser(
              name = "Johna Doe",
              points = 80,
            ),
          )
          LeaderboardRank(
            rank = 1,
            user = LeaderboardUser(
              name = "John Doe",
              points = 100,
            ),
          )
          LeaderboardRank(
            rank = 3,
            user = LeaderboardUser(
              name = "Johna Doane",
              points = 70,
            ),
          )
        }
      }
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.5f)
          .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
          )
          .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
          .zIndex(1f)
          .padding(24.dp)
      )
      {
        items(10) { rowId ->
          LeaderboardUserListItem(
            rank = rowId + 4,
            user = LeaderboardUser(
              name = "Joane Doe",
              points = Random.nextInt(0, 69),
            ),
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LeaderBoardScreenPreview() {
  FootworkTheme {
    LeaderBoardScreen()
  }
}
