package com.gi2.footwork.features.Challenge.presentation.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gi2.footwork.features.Challenge.domain.model.LeaderboardUser
import com.gi2.footwork.ui.composables.atoms.ShimmerBox
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun LeaderboardRank(
  modifier: Modifier = Modifier,
  user: LeaderboardUser,
  rank: Int,
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = Modifier
        .width(56.dp)
        .height(56.dp)
        .clip(RoundedCornerShape(100.dp))
        .border(
          width = 2.dp,
          color = MaterialTheme.colorScheme.background,
          shape = RoundedCornerShape(100.dp)
        )
    ) {
      CoilImage(
        modifier = Modifier.fillMaxSize(),
        imageModel = { user.getAvatarUrl() },
        imageOptions = ImageOptions(
          contentScale = ContentScale.FillBounds,
          alignment = Alignment.Center
        ),
        loading = { ShimmerBox(modifier = Modifier.size(64.dp)) },
        failure = {
          ShimmerBox(
            modifier = Modifier.size(64.dp),
            animate = false
          )
        },
      )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      user.name,
      style = MaterialTheme.typography.titleSmall,
      color = onPrimaryFixed,
      fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Column(
      modifier = modifier
        .background(
          color = MaterialTheme.colorScheme
            .background
            .copy(alpha = 0.5f),
          shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
        )
        .border(
          width = 2.dp,
          color = MaterialTheme.colorScheme.background,
          shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
        )
        .width(100.dp)
        .height(
          when (rank) {
            1 -> 160.dp
            2 -> 120.dp
            3 -> 100.dp
            else -> 100.dp
          }
        ),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        "$rank",
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.background
      )
      Text(
        "${user.points} pts",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.background
      )
    }
  }
}
