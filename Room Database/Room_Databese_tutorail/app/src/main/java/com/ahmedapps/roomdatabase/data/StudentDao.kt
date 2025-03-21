package com.ahmedapps.roomdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("UPDATE students SET isPassing = :isPassing WHERE id = :studentId")
    suspend fun updatePassingStatus(studentId: Long, isPassing: Boolean)
}