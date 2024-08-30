package com.wefit.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

fun generateToken(userId: Int): String {
    val algorithm = Algorithm.HMAC256("secret")
    return JWT.create()
        .withAudience("ktor_audience")
        .withIssuer("ktor.io")
        .withClaim("userId", userId)
        .withExpiresAt(Date(System.currentTimeMillis() + 60000)) // Token expires in 1 minute
        .sign(algorithm)
}