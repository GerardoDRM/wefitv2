package com.wefit.workouts

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Resource("/workouts")
class Workouts {
    @Resource("/{id}")
    class Id
}



@Serializable
data class Workout(
	val name: String
)