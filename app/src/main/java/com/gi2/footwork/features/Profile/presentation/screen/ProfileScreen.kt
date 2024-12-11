package com.gi2.footwork.features.Profile.presentation.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Profile.domain.models.Stats
import com.gi2.footwork.features.Profile.model.SettingsItem
import com.gi2.footwork.features.Profile.model.SettingsList
import com.gi2.footwork.features.Profile.presentation.atoms.ProfileStatsBar
import com.gi2.footwork.ui.composables.common.drawRipple
import com.gi2.footwork.ui.theme.*

@Composable
@Preview(showSystemUi = true)
fun ProfileScreen() {
  val name = "Dianne Russell"
  val email = "debra.holt@example.com"

  Scaffold { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(color = primaryFixed)
        .drawRipple()
        .padding(top = innerPadding.calculateTopPadding()),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ProfileAvatarImage(
        avatar = R.drawable.user_avatar,
        onClick = {}
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = name,
        style = AppTypography.titleLarge.copy(
          fontWeight = FontWeight(600),
          color = onPrimaryFixed
        )
      )
      Spacer(modifier = Modifier.height(8.dp))
      ProfileEmailBadge(email = email)
      Spacer(modifier = Modifier.height(16.dp))
      ProfileStatsBar(
        stats = Stats(emission = 0, mileage = 0, point = 0),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp)
          .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
          )
      )
      Spacer(modifier = Modifier.height(32.dp))
      SettingsCard()
    }
  }
}

@Composable
fun SettingsCardItem(
  @DrawableRes icon: Int = R.drawable.ic_user,
  name: String = "",
  onClick: () -> Unit = {},
  trailingText: String? = null,
  isToggleable: Boolean = false,
  toggledState: Boolean = false,
  onToggled: ((Boolean) -> Unit)? = null,
  modifier: Modifier = Modifier,
) {
  var checkedState by remember { mutableStateOf(toggledState) }

  Row(
    modifier = modifier
      .padding(vertical = 8.dp)
      .clickable { onClick() },
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Box(
      modifier = Modifier
        .size(40.dp)
        .background(
          color = primaryFixed,
          shape = RoundedCornerShape(8.dp)
        ),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = onPrimaryFixed
      )
    }

    Text(
      text = name,
      modifier = Modifier.weight(1f),
      style = AppTypography.titleMedium.copy(
        color = homeSectionTitle
      )
    )

    if (isToggleable) {
      Switch(
        checked = checkedState,
        onCheckedChange = {
          checkedState = it
          onToggled?.invoke(it)
        },
        colors = SwitchDefaults.colors(
          checkedThumbColor = primaryFixed,
          checkedTrackColor = primaryFixed.copy(alpha = 0.5f)
        )
      )
    } else {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        if (trailingText != null) {
          Text(
            text = trailingText,
            style = AppTypography.titleSmall.copy(
              color = userGreetingSubText
            )
          )
        }

        Icon(
          painter = painterResource(id = R.drawable.ic_chevron_right),
          contentDescription = null
        )
      }
    }
  }
}

@Composable
fun SettingsCard(
  settingsList: List<SettingsItem> = SettingsList,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .background(
        color = onPrimaryFixed,
        shape = RoundedCornerShape(
          topStart = 24.dp,
          topEnd = 24.dp,
          bottomStart = 0.dp,
          bottomEnd = 0.dp
        )
      )
      .padding(24.dp),
  ) {
    settingsList.forEach { item ->
      SettingsCardItem(
        icon = item.icon,
        name = item.name,
        onClick = item.onClick,
        trailingText = item.trailingText,
        isToggleable = item.isToggleable,
        toggledState = item.toggledState,
        onToggled = item.onToggled
      )
    }

    Spacer(modifier = Modifier.weight(1f))

    SettingsCardItem(
      icon = R.drawable.ic_logout,
      name = "Logout",
      onClick = {},
      modifier = Modifier.padding(top = 16.dp)
    )
  }
}

@Composable
fun ProfileEmailBadge(
  email: String = "debra.holt@example.com",
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .background(
        color = onPrimaryFixed.copy(
          alpha = 0.25f
        ),
        shape = RoundedCornerShape(24.dp)
      )
      .border(
        width = 1.dp,
        color = onPrimaryFixed,
        shape = RoundedCornerShape(24.dp)
      )
      .padding(
        horizontal = 10.dp,
        vertical = 6.dp
      )
  ) {
    Text(
      text = email,
      style = AppTypography.bodyMedium.copy(
        color = onPrimaryFixed
      )
    )
  }
}

@Composable
fun ProfileAvatarImage(
  avatar: Int = R.drawable.user_avatar,
  onClick: () -> Unit = {},
  modifier: Modifier = Modifier
    .size(80.dp),
) {
  Box(
    modifier = modifier
      .clickable(onClick = onClick)
  ) {
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(40.dp))
        .background(
          color = onPrimaryFixed
        )
        .border(
          width = 2.dp,
          color = onPrimaryFixed,
          shape = RoundedCornerShape(40.dp)
        )
    ) {
      Image(
        painter = painterResource(id = avatar),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    Box(
      modifier = Modifier
        .size(24.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(
          color = primaryFixed
        )
        .border(
          width = 2.dp,
          color = onPrimaryFixed,
          shape = RoundedCornerShape(12.dp)
        )
        .align(Alignment.BottomEnd)
    ) {
      Icon(
        imageVector = Icons.Outlined.Add,
        contentDescription = null,
        tint = onPrimaryFixed,
        modifier = Modifier
          .align(Alignment.Center)
          .padding(2.dp)
      )
    }
  }
}
