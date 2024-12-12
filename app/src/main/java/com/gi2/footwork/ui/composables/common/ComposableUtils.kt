package com.gi2.footwork.ui.composables.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gi2.footwork.ui.theme.onPrimaryFixed

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.scopedViewModel(
  navController: NavController,
): T {
  val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
  val parentEntry: NavBackStackEntry = remember(this) {
    navController.getBackStackEntry(navGraphRoute)
  }
  return hiltViewModel(parentEntry)
}

@Composable
fun keyboardAsState(): State<Boolean> {
  val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
  return rememberUpdatedState(isImeVisible)
}

//fun getLocalizedString(context: Context, strResId: String): String {
//  val resId = strResId.toIntOrNull() ?: return ""
//  return context.getString(resId)
//}

fun Modifier.drawRipple(color: Color = onPrimaryFixed): Modifier = this.then(
  Modifier.drawBehind {
    val circles = listOf(235.dp, 189.dp, 131.dp)

    circles.forEach { radius ->
      drawCircle(
        color = color.copy(alpha = 0.1f),
        radius = radius.toPx(),
        center = Offset(
          size.width / 2,
          size.height / 3 - 20.dp.toPx()
        ),
        style = Stroke(4.dp.toPx())
      )
      drawCircle(
        color = color.copy(alpha = 0.1f),
        radius = radius.toPx(),
        center = Offset(
          size.width / 2,
          size.height / 3 - 20.dp.toPx()
        )
      )
    }
  }
)
