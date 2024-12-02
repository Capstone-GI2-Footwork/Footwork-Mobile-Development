package com.gi2.footwork.features.Profile.presentation.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.features.Profile.model.SettingsItem
import com.gi2.footwork.features.Profile.model.SettingsList
import com.gi2.footwork.ui.theme.AppTypography
import com.gi2.footwork.ui.theme.homeSectionTitle
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import com.gi2.footwork.ui.theme.userGreetingSubText

@Composable
//@Preview(apiLevel = 30, showBackground = true, backgroundColor = 0xFFFFFFFF)
fun SettingsCardItem(
    @DrawableRes icon: Int = R.drawable.ic_user,
    name: String = "",
    onClick: () -> Unit = {},
    trailingText: String? = null,
    isToggleable: Boolean = false,
    toggledState: Boolean = false,
    onToggled: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
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
            modifier = Modifier.size(40.dp)
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
                    onToggled?.invoke(it) },
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
//@Preview(apiLevel = 30)
fun SettingsCard(
    settingsList: List<SettingsItem> = SettingsList,
    modifier: Modifier = Modifier
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
            onClick = {
                /* TODO */
            },
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
//@Preview(apiLevel = 30)
fun ProfileStatsBar(
    emisi: String = "0",
    jarakTempuh: String = "0",
    poin: String = "0",
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(60.dp)
            .background(
                color = onPrimaryFixed,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ){
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
                    append("$emisi CO")
                    withStyle(
                        style = SpanStyle(
                            baselineShift = BaselineShift.Superscript,
                            fontSize = AppTypography.labelMedium.fontSize * 0.75
                        )
                    ) {
                        append("2")
                    }
                    append("g")
                },
                style = AppTypography.labelMedium.copy(
                    color = primaryFixed
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        VerticalDivider(
            thickness = 2.dp,
            color = primaryFixed,
            modifier = Modifier.height(48.dp)
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
                text = "$jarakTempuh km",
                style = AppTypography.labelMedium.copy(
                    color = primaryFixed
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        VerticalDivider(
            thickness = 2.dp,
            color = primaryFixed,
            modifier = Modifier.height(48.dp)
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
                text = "$poin pts",
                style = AppTypography.labelMedium.copy(
                    color = primaryFixed
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
//@Preview(apiLevel = 30, showBackground = true, backgroundColor = 0xFF52B040)
fun ProfileEmailBadge(
    email: String = "debra.holt@example.com",
    modifier: Modifier = Modifier
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
    ){
        Text(
            text = email,
            style = AppTypography.bodyMedium.copy(
                color = onPrimaryFixed
            )
        )
    }
}

@Composable
//@Preview(apiLevel = 30)
fun ProfileAvatarImage(
    avatar: Int = R.drawable.user_avatar,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .size(80.dp)
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
    ){
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
        ){
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                tint = onPrimaryFixed,
                modifier = Modifier.align(Alignment.Center)
                    .padding(2.dp)
            )
        }
    }
}

@Composable
@Preview(apiLevel = 30, showSystemUi = true)
fun ProfileScreen(){
    val name = "Dianne Russell"
    val email = "debra.holt@example.com"

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = primaryFixed
                )
                .drawBehind { 
                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 235.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        ),
                        style = Stroke(4.dp.toPx())
                    )

                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 235.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        )
                    )

                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 189.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        ),
                        style = Stroke(4.dp.toPx())
                    )

                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 189.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        )
                    )

                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 131.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        ),
                        style = Stroke(4.dp.toPx())
                    )

                    drawCircle(
                        color = onPrimaryFixed.copy(alpha = 0.1f),
                        radius = 131.dp.toPx(),
                        center = Offset(
                            size.width / 2,
                            size.height / 3 - 20.dp.toPx()
                        )
                    )
                }
                .padding(top = innerPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAvatarImage(
                avatar = R.drawable.user_avatar,
                onClick = {
                    /* TODO */
                }
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

            ProfileEmailBadge(
                email = email
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileStatsBar(
                emisi = "0",
                jarakTempuh = "0",
                poin = "0",
                modifier = Modifier.fillMaxWidth()
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