package com.kiran.softuserroomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiran.softuserroomdatabase.dao.StudentDAO
import com.kiran.softuserroomdatabase.dao.UserDAO
import com.kiran.softuserroomdatabase.entity.Student
import com.kiran.softuserroomdatabase.entity.User

@Database(
    entities = [(Student::class), (User::class)],
    version = 2,
    exportSchema = false
)
abstract class StudentDB : RoomDatabase() {
    abstract fun getStudentDAO(): StudentDAO
    abstract fun getUserDAO() : UserDAO

    companion object {
        @Volatile
        private var instance: StudentDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
            context.applicationContext,
            StudentDB::class.java,
            "StudentDB"
        ).build()
    }
}