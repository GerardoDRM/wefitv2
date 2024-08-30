package com.wefit.model

import com.wefit.model.User
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import com.wefit.db.suspendTransaction
import com.wefit.db.UserDAO
import com.wefit.db.UserTable
import com.wefit.db.daoUserToModel


class PostgresUserRepository : UserRepository {
    override suspend fun findUserByEmail(email: String): User? = suspendTransaction {
        UserDAO.find { UserTable.email eq email }.firstOrNull()?.let(::daoUserToModel)
    }
}