package com.gi2.footwork.features.Community.model

data class UserStoriesItemModel(
    val sourceImage: Int,
    val username: String,
    val onClick: () -> Unit,
    val isViewed: Boolean
)