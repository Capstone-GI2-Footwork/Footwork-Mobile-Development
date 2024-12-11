package com.gi2.footwork.features.Challenge.presentation.atoms

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.features.Challenge.domain.model.LeaderboardUser
import com.gi2.footwork.ui.composables.atoms.ShimmerBox
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import com.skydoves.landscapist.coil3.CoilImage

@SuppressLint("DefaultLocale")
@Composable
fun LeaderboardUserListItem(
  modifier: Modifier = Modifier,
  user: LeaderboardUser,
  rank: Int,
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      String.format("%02d", rank),
      fontWeight = FontWeight.SemiBold
    )
    ListItem(
      modifier = modifier.fillMaxWidth(),
      leadingContent = {
        CoilImage(
          imageModel = { user.getAvatarUrl() },
          loading = { ShimmerBox(modifier = Modifier.size(64.dp)) },
          failure = {
            ShimmerBox(
              modifier = Modifier.size(64.dp),
              animate = false
            )
          },
          modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.small)
        )
      },
      headlineContent = { Text(user.name) },
      trailingContent = {
        Box(
          modifier = Modifier
            .background(
              color = primaryFixed,
              shape = RoundedCornerShape(100.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            "${user.points} pts",
            color = onPrimaryFixed,
            fontWeight = FontWeight.SemiBold
          )
        }
      }
    )
  }
}

@Preview(showBackground = true)
@Composable
private fun LeaderboardUserListItemPreview() {
  FootworkTheme {
    Box(
      modifier = Modifier.padding(16.dp)
    ) {
      LeaderboardUserListItem(
        rank = 4,
        user = LeaderboardUser(
          name = "John Doe",
          points = 100
        )
      )
    }
  }
}
