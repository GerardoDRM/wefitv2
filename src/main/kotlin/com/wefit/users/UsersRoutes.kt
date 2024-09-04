package com.wefit.users

import com.wefit.users.Login
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.resources.post
import io.ktor.server.routing.*
import com.wefit.model.User
import com.wefit.model.UserRepository
import com.wefit.utils.generateToken


fun Application.userRoutes(userRepository: UserRepository) {
    routing {
        post("/login") {
            // Validate user credentials here
            val loginData = call.receive<Login>()
            if (loginData.email == null || loginData.email.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Email is required")
                return@post
            }
            val user = userRepository.findUserByEmail(loginData.email)
            if (user != null && user.isActive) {
                val token = generateToken(user.id)
                call.respond(mapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
         }
    }
}
