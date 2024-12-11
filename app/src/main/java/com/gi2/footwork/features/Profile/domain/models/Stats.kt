package com.gi2.footwork.features.Profile.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
  val emission: Int,
  val point: Int,
  val mileage: Int,
)
