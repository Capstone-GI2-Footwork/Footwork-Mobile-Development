package com.gi2.footwork.features.Profile.presentation.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Profile.domain.models.Stats
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.FootworkTheme
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed

@Composable
fun ProfileStatsBar(
  stats: Stats,
  modifier: Modifier = Modifier,
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(
      8.dp,
      Alignment.CenterHorizontally
    ),
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .height(60.dp)
      .background(
        color = onPrimaryFixed,
        shape = RoundedCornerShape(16.dp)
      )
      .padding(8.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painter = painterResource(id = R.drawable.ic_wind),
          contentDescription = null,
          tint = primaryFixed
        )
        Text(
          text = "Emisi",
          style = AppTypography.titleSmall.copy(
            color = primaryFixed
          )
        )
      }

      Text(
        text = buildAnnotatedString {
          append("${stats.emission} CO")
          withStyle(
            style = SpanStyle(
              baselineShift = BaselineShift.Superscript,
              fontSize = AppTypography.labelMedium.fontSize * 0.75
            )
          ) { append("2") }
          append("g")
        },
        style = AppTypography.labelMedium.copy(color = primaryFixed),
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
    }

    VerticalDivider(
      thickness = 2.dp,
      color = primaryFixed,
      modifier = Modifier
        .height(48.dp)
        .clip(RoundedCornerShape(8.dp))
    )

    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painter = painterResource(id = R.drawable.ic_routing),
          contentDescription = null,
          tint = primaryFixed
        )
        Text(
          text = "Jarak Tempuh",
          style = AppTypography.titleSmall.copy(
            color = primaryFixed
          )
        )
      }

      Text(
        text = "${stats.mileage} km",
        style = AppTypography.labelMedium.copy(
          color = primaryFixed
        ),
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
    }

    VerticalDivider(
      thickness = 2.dp,
      color = primaryFixed,
      modifier = Modifier
        .height(48.dp)
        .clip(RoundedCornerShape(8.dp))
    )

    Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          painter = painterResource(id = R.drawable.ic_cup_star),
          contentDescription = null,
          tint = primaryFixed
        )
        Text(
          text = "Poin",
          style = AppTypography.titleSmall.copy(
            color = primaryFixed
          )
        )
      }

      Text(
        text = "${stats.point} pts",
        style = AppTypography.labelMedium.copy(
          color = primaryFixed
        ),
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun ProfileStatsBarPreview() {
  FootworkTheme {
    Box(modifier = Modifier.padding(16.dp)) {
      ProfileStatsBar(
        stats = Stats(
          emission = 100,
          mileage = 1000,
          point = 100
        )
      )
    }
  }
}
