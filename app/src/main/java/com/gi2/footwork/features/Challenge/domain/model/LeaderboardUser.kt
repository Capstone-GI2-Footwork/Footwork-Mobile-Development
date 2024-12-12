package com.gi2.footwork.features.Challenge.domain.model

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class LeaderboardUser @OptIn(ExperimentalUuidApi::class) constructor(
  val id: String = Uuid.random().toString(),
  val name: String,
  val points: Int,
) {
  fun getAvatarUrl(): String = "https://avatars.jakerunzer.com/$id"
}
