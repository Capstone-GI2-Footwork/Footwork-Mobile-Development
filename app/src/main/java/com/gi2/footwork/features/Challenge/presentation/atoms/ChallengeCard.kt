package com.gi2.footwork.features.Challenge.presentation.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gi2.footwork.R
import com.gi2.footwork.features.Challenge.domain.models.Challenge
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed

@Composable
fun ChallengeCard(challenge: Challenge) {
  val challengeProgress = challenge.progress.toFloat() / challenge.finishCount

  Column(
    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start,
    modifier = Modifier
      .shadow(
        elevation = 8.dp,
        spotColor = Color(0x14000000),
        ambientColor = Color(0x14000000)
      )
      .width(345.dp)
      .background(
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(size = 12.dp)
      )
      .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
  ) {
    Text(
      text = challenge.name,
      style = AppTypography.titleMedium.copy(
        fontWeight = FontWeight(500), color = homeSectionTitle
      )
    )

    Box(
      contentAlignment = Alignment.CenterStart,
      modifier = Modifier.fillMaxWidth()
    ) {
      val isCompleted = (challenge.progress == challenge.finishCount)

      if (isCompleted) {
        ClaimButton(
          modifier = Modifier
            .align(Alignment.CenterStart)
            .fillMaxWidth()
            .padding(end = 64.dp)
            .zIndex(1f)
            .height(54.dp),
          onClick = {}
        )
      }
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .border(
            width = 1.dp,
            color = Color(0xFFCDCDCD),
            shape = RoundedCornerShape(size = 10.dp)
          )
          .padding(4.dp)
      ) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .weight(1f)
            .height(24.dp)
        ) {
          ChallengeProgressIndicator(
            progress = challengeProgress,
            modifier = Modifier.height(24.dp)
          )
          Text(
            text = "${challenge.progress}/${challenge.finishCount}",
            style = AppTypography.labelLarge.copy(
              fontWeight = FontWeight(600),
              color = onPrimaryFixed
            )
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = challenge.progress.toString(),
          style = AppTypography.titleLarge.copy(
            fontWeight = FontWeight(600), color = Color(0xFFEAB01C)
          )
        )
        Image(
          painter = painterResource(id = R.drawable.ic_cup_star),
          contentDescription = "Trophy icon",
          contentScale = ContentScale.None
        )
      }
    }
  }
}
