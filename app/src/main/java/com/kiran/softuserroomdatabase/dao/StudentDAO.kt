package com.kiran.softuserroomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kiran.softuserroomdatabase.entity.Student

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student : Student)

    @Query("SELECT * FROM Student")
    suspend fun getAllStudents() : List<Student>
}