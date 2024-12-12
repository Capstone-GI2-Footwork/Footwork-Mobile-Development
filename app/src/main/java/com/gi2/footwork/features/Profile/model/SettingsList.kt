package com.gi2.footwork.features.Profile.model

import com.gi2.footwork.R

val SettingsList = listOf(
    SettingsItem(
        icon = R.drawable.ic_user,
        name = "Edit Profile",
        onClick = {},
    ),
    SettingsItem(
        icon = R.drawable.ic_global,
        name = "Bahasa",
        onClick = {},
        trailingText = "Indonesia"
    ),
    SettingsItem(
        icon = R.drawable.ic_dark_mode,
        name = "Mode Gelap",
        onClick = {},
        isToggleable = true,
        toggledState = false,
        onToggled = {}
    ),
    SettingsItem(
        icon = R.drawable.ic_settings,
        name = "Pengaturan",
        onClick = {},
    ),
    SettingsItem(
        icon = R.drawable.ic_faq,
        name = "FAQ",
        onClick = {},
    ),
    SettingsItem(
        icon = R.drawable.ic_info,
        name = "About App",
        onClick = {},
    ),
)