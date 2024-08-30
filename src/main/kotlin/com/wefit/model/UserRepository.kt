package com.wefit.model

import com.wefit.model.User

interface UserRepository {
    suspend fun findUserByEmail(email: String): User?
}
