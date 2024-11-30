package com.gi2.footwork.features.Profile.model

data class SettingsItem(
    val icon: Int,
    val name: String,
    val onClick: () -> Unit,
    val trailingText: String? = null,
    val isToggleable: Boolean = false,
    val toggledState: Boolean = false,
    val onToggled: ((Boolean) -> Unit)? = null
)