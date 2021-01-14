package com.kiran.softuserroomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import com.kiran.softuserroomdatabase.entity.User

@Dao
interface UserDAO {
    @Insert
    suspend fun registerUser(user : User)
}