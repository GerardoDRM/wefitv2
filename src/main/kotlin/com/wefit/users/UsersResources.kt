package com.wefit.users

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
data class Login(val email: String)
