package com.gi2.footwork.features.Community.model

data class UserPostsItemModel(
    val sourceImage: Int,
    val userAvatar: Int,
    val username: String,
    val location: String,
    val time: String,
    val likeCount: Int,
    val commentCount: Int,
    val description: String,
    val onClick: () -> Unit,
)
