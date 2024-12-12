package com.gi2.footwork.features.Challenge.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gi2.footwork.features.Challenge.domain.model.Challenge
import com.gi2.footwork.features.Challenge.domain.model.LeaderboardUser
import com.gi2.footwork.features.Challenge.presentation.atoms.ChallengeCard
import com.gi2.footwork.features.Challenge.presentation.atoms.LeaderboardRank
import com.gi2.footwork.features.Challenge.presentation.atoms.LeaderboardTab
import com.gi2.footwork.features.Challenge.presentation.atoms.LeaderboardUserListItem
import com.gi2.footwork.features.Profile.domain.model.Stats
import com.gi2.footwork.features.Profile.presentation.atoms.ProfileStatsBar
import com.gi2.footwork.ui.composables.common.drawRipple
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import kotlin.random.Random

@Composable
fun LeaderboardScreen(
  modifier: Modifier = Modifier,
) {
  var currentIndex by remember { mutableIntStateOf(0) }

  LeaderboardScreenContent(
    modifier = modifier,
    currentPage = currentIndex,
    onPageChanged = { index -> currentIndex = index }
  )
}

@Composable
private fun LeaderboardScreenContent(
  modifier: Modifier = Modifier,
  currentPage: Int = 0,
  onPageChanged: (Int) -> Unit = {},
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
      Box(
        modifier = Modifier.padding(horizontal = 24.dp)
      ) {
        LeaderboardTab(
          currentIndex = currentPage,
          onTabSelected = onPageChanged
        )
      }
      Spacer(modifier = Modifier.height(32.dp))
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .height(IntrinsicSize.Min)
          .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        when (currentPage) {
          0 -> FinalistsRank()
          1 -> Box(
            modifier = Modifier.padding(bottom = 16.dp)
          ) {
            ProfileStatsBar(
              modifier = Modifier.fillMaxWidth(),
              stats = Stats(
                emission = Random.nextInt(0, 500),
                point = Random.nextInt(0, 1000),
                mileage = Random.nextInt(0, 1000)
              ),
            )
          }

          else -> FinalistsRank()
        }
      }

      when (currentPage) {
        0 -> LeaderboardUsers()
        1 -> DailyTasks()
        else -> LeaderboardUsers()
      }
    }
  }
}

@Composable
fun FinalistsRank() {
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

@Composable
private fun LeaderboardUsers() {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
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

@Composable
private fun DailyTasks() {
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
      )
      .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
      .zIndex(1f)
      .padding(24.dp)
  )
  {
    items(10) {
      ChallengeCard(
        challenge = Challenge(
          name = "Share positive moments",
          progress = Random.nextInt(1, 10),
          finishCount = 10
        )
      )
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LeaderBoardScreenWeeklyPreview() {
  FootworkTheme {
    LeaderboardScreenContent(
      currentPage = 0,
      onPageChanged = { }
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LeaderboardScreenDailyTasksPreview() {
  FootworkTheme {
    LeaderboardScreenContent(
      currentPage = 1,
      onPageChanged = { }
    )
  }
}
