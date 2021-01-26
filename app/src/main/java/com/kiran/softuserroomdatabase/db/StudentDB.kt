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
    abstract fun getUserDAO(): UserDAO

    companion object {
        @Volatile
        private var instance: StudentDB? = null

        fun getInstance(context: Context): StudentDB {
            if (instance == null) {
                synchronized(StudentDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StudentDB::class.java,
                "StudentDB"
            ).build()
    }
}