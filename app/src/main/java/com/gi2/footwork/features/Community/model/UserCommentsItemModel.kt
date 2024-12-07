package com.gi2.footwork.features.Community.model

data class UserCommentsItemModel(
    val userAvatar: Int,
    val username: String,
    val time: String,
    val comment: String,
    val likeCount: Int,
    val commentCount: Int
)