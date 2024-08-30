package com.wefit.workouts

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.resources.get
import io.ktor.server.routing.*
import com.wefit.workouts.Workouts
import com.wefit.workouts.Workout

fun Application.workoutsRoutes() {
    routing {
        get<Workouts> {
            val workoluts = Workout(name="workout1")
            call.respond(workoluts)
        }
    }
}