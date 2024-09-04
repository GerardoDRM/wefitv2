package com.wefit.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.Transaction
import kotlinx.coroutines.Dispatchers
import com.wefit.db.ExerciseTable
import com.wefit.model.Workout
import com.wefit.model.Set


object WorkoutTable : IntIdTable() {
    val dayOfWeek = varchar("day_of_week", 50)
    val userId = reference("user_id", UserTable)
    val exerciseId = reference("exercise_id", ExerciseTable)
}

object SetTable : IntIdTable() {
    val workoutId = reference("workout_id", WorkoutTable)
    val setNumber = integer("set_number")
    val repetitions = integer("repetitions")
    val weight = decimal("weight", precision = 10, scale = 2)
}

class SetDAO (id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SetDAO>(SetTable)

    var workout by WorkoutDAO referencedOn SetTable.workoutId
    var setNumber by SetTable.setNumber
    var repetitions by SetTable.repetitions
    var weight by SetTable.weight
}

class WorkoutDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WorkoutDAO>(WorkoutTable)

    var dayOfWeek by WorkoutTable.dayOfWeek
    var exercise by ExerciseDAO referencedOn WorkoutTable.exerciseId
    val sets by SetDAO referrersOn SetTable.workoutId
}

fun daoWorkoutToModel(dao: WorkoutDAO) = Workout(
    dao.id.value,
    dao.dayOfWeek,
    dao.exercise.let { daoExerciseoModel(it) },
    dao.sets.map { daoSetToModel(it) }.toTypedArray(),
)

fun daoSetToModel(dao: SetDAO) = Set(
    dao.repetitions,
    dao.weight.toDouble(),
)