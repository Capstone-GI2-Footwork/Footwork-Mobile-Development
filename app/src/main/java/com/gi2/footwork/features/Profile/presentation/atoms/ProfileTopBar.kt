package com.gi2.footwork.features.Profile.presentation.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.userGreetingSubText

@Composable
fun ProfileTopBar(
  modifier: Modifier = Modifier,
  userName: String = "Arnold",
  date: String = "Aug 17, 2024",
  userPhoto: Int = R.drawable.user_avatar,
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .fillMaxWidth()
      .height(40.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(start = 16.dp)
    ) {
      Box(
        contentAlignment = Alignment.Center, modifier = Modifier
          .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            ambientColor = Color(0x1A000000)
          )
          .background(
            color = Color(0xFFFEFEFE),
            shape = RoundedCornerShape(size = 8.dp)
          )
          .alpha(0.75f)
          .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_bell),
          contentDescription = "image description",
          contentScale = ContentScale.None
        )
      }

      Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
      ) {
        Text(
          text = "Hi $userName",

          style = AppTypography.titleMedium.copy(
            fontWeight = FontWeight(600),
            color = homeSectionTitle,
          )
        )

        Text(
          text = date,

          style = AppTypography.bodySmall.copy(
            fontWeight = FontWeight(400), color = userGreetingSubText
          )
        )
      }
    }

    Box(
      modifier = Modifier
        .width(40.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(8.dp))
    ) {
      Image(
        painter = painterResource(id = userPhoto),
        contentDescription = "user avatar",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
