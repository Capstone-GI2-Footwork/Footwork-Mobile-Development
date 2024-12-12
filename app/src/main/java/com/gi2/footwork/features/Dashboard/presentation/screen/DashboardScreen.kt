package com.gi2.footwork.features.Dashboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Profile.domain.model.Stats
import com.gi2.footwork.features.Profile.presentation.atoms.ProfileStatsBar
import com.gi2.footwork.features.Profile.presentation.atoms.ProfileTopBar
import com.gi2.footwork.ui.theme.*
import kotlin.random.Random

@Composable
fun DashboardScreen(
  modifier: Modifier = Modifier,
) {
  DashboardScreenContent(
    modifier = modifier
  )
}

@Composable
private fun DashboardScreenContent(
  modifier: Modifier = Modifier,
) {
  val menuItems = listOf(
    DashboardMenuItem(
      title = "Steps",
      icon = R.drawable.ic_footprints,
      gradient = redVerticalGradient,
      color = Color(0xFFB22F47)
    ),
    DashboardMenuItem(
      title = "Car",
      icon = R.drawable.ic_car,
      gradient = blueVerticalGradient,
      color = Color(0xFF2F80B2)
    ),
    DashboardMenuItem(
      title = "Motorcycle",
      icon = R.drawable.ic_motorcycle,
      gradient = magentaVerticalGradient,
      color = Color(0xFFB22F84)
    ),
    DashboardMenuItem(
      title = "EV Cycle",
      icon = R.drawable.ic_ev_cycle,
      gradient = purpleVerticalGradient,
      color = Color(0xFF752FB2)
    ),
    DashboardMenuItem(
      title = "EV Car",
      icon = R.drawable.ic_car,
      gradient = greenVerticalGradient,
      color = Color(0xFF36B22F)
    ),
  )

  var currentIndex by remember { mutableIntStateOf(0) }

  Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
      ProfileTopBar(
        modifier = Modifier
          .safeDrawingPadding()
          .padding(vertical = 16.dp)
          .padding(start = 12.dp, end = 24.dp)
      )
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      verticalArrangement = Arrangement.spacedBy(24.dp),
      contentPadding = PaddingValues(
        top = 0.dp,
        start = 24.dp,
        bottom = 24.dp,
        end = 24.dp
      )
    ) {
      item {
        LazyRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          itemsIndexed(menuItems) { index, item ->
            Column(
              modifier = Modifier
                .background(
                  brush =
                  if (index == currentIndex) item.gradient
                  else Brush.verticalGradient(
                    colors = listOf(
                      onPrimaryFixed,
                      onPrimaryFixed
                    )
                  ),
                  shape = MaterialTheme.shapes.small
                )
                .border(
                  width = 1.dp,
                  color =
                  if (index != currentIndex) MaterialTheme.colorScheme.surfaceContainerHigh
                  else Color.Transparent,
                  shape = MaterialTheme.shapes.small
                )
                .width(100.dp)
                .height(88.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .clickable { currentIndex = index }
                .padding(8.dp),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                  .width(32.dp)
                  .height(32.dp),
                tint =
                if (index == currentIndex) onPrimaryFixed
                else item.color
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                item.title,
                style = MaterialTheme.typography.labelLarge,
                color =
                if (index == currentIndex) onPrimaryFixed
                else item.color,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
        }
      }
      item {
        ProfileStatsBar(
          modifier = Modifier
            .fillMaxWidth()
            .border(
              width = 1.dp,
              color = MaterialTheme.colorScheme.surfaceContainerHigh,
              shape = MaterialTheme.shapes.small
            ),
          stats = Stats(
            emission = Random.nextInt(0, 500),
            point = Random.nextInt(0, 1000),
            mileage = Random.nextInt(0, 1000)
          ),
          showPoint = false
        )
      }
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = primaryFixed)
        ) {

        }
      }
    }
  }
}

@Immutable
data class DashboardMenuItem(
  val id: Int = Random.nextInt(),
  val title: String,
  val icon: Int,
  val gradient: Brush,
  val color: Color,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DashboardScreenPreview() {
  FootworkTheme {
    DashboardScreenContent()
  }
}
