package com.wefit.plugins

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        route("/tasks") {
            get {
                call.respond(HttpStatusCode.OK)
                return@get
            }
        }
    }
}