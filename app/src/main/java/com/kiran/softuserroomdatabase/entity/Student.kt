package com.kiran.softuserroomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    var fname: String? = null,
    var lname: String? = null,
    var username: String? = null,
    var password: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var stdId: Int = 0
}