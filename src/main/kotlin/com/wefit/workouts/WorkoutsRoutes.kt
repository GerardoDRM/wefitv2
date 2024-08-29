package com.wefit.workouts

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.workoutsRoutes() {
    routing {
        route("/workouts") {
            get {
                call.respond(HttpStatusCode.OK)
                return@get
            }
        }
    }
}