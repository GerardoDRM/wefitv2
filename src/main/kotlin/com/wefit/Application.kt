package com.wefit

import com.wefit.plugins.configureSerialization
import com.wefit.workouts.workoutsRoutes
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    workoutsRoutes()
}