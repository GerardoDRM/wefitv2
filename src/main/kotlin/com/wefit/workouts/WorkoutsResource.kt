package com.wefit.workouts

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/workouts")
class Workouts {

}

@Serializable
data class Workout(
	val name: String
)