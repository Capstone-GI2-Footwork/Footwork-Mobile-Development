package com.gi2.footwork.features.Challenge.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Challenge(
  val name: String,
  val progress: Int,
  val finishCount: Int,
)
