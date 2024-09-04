package com.wefit.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.Transaction
import kotlinx.coroutines.Dispatchers
import com.wefit.model.User


object UserTable : IntIdTable() {
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val isActive = bool("is_active").default(false)
}

class UserDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDAO>(UserTable)

    var name by UserTable.name
    var email by UserTable.email
    var isActive by UserTable.isActive
}


suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)


fun daoUserToModel(dao: UserDAO) = User(
    dao.id.value,
    dao.name,
    dao.email,
    dao.isActive,
)