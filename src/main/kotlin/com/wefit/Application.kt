package com.wefit

import com.wefit.plugins.configureSerialization
import com.wefit.plugins.configureDatabases
import com.wefit.workouts.workoutsRoutes
import com.wefit.users.userRoutes
import io.ktor.server.application.*
import io.ktor.server.resources.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.wefit.db.UserTable
import com.wefit.db.WorkoutTable
import com.wefit.db.ExerciseTable
import com.wefit.db.SetTable
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import com.wefit.model.PostgresUserRepository

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = PostgresUserRepository()

    install(Resources)
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor.io"
            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withAudience("ktor_audience")
                    .withIssuer("ktor.io")
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains("ktor_audience")) JWTPrincipal(credential.payload) else null
            }
            challenge { _, _ ->
                call.respond(UnauthorizedResponse())
            }
        }
    }
    configureSerialization()
    configureDatabases()
    // Initialize the database and create tables if they don't exist
    transaction {
        SchemaUtils.create(UserTable) // This will create the Users table if it doesn't exist

        SchemaUtils.createMissingTablesAndColumns(UserTable)
        SchemaUtils.createMissingTablesAndColumns(ExerciseTable)
        SchemaUtils.createMissingTablesAndColumns(WorkoutTable)
        SchemaUtils.createMissingTablesAndColumns(SetTable)
    }
    workoutsRoutes()
    userRoutes(repository)
}