package com.wefit.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: Int,
    val dayOfWeek: String,
    val exercise: Exercise,
    val sets: Array<Set>
)

@Serializable
data class Set(
    val repetitions: Int,
    val weight: Double
)
