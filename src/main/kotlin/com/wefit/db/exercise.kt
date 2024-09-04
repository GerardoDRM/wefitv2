package com.wefit.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.Transaction
import kotlinx.coroutines.Dispatchers
import com.wefit.model.Exercise


object ExerciseTable : IntIdTable() {
    val name = varchar("name", 50)
    val description = varchar("description", 250)
    val imgS3 = varchar("img_s3", 250)
    val videoRef = varchar("video", 250)
}

class ExerciseDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ExerciseDAO>(ExerciseTable)

    var name by ExerciseTable.name
    var description by ExerciseTable.description
    var imgS3 by ExerciseTable.imgS3
}

fun daoExerciseoModel(dao: ExerciseDAO) = Exercise(
    dao.id.value,
    dao.name,
    dao.description,
    dao.imgS3,
)