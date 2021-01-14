package com.kiran.softuserroomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    var fullName: String? = null,
    var age: Int? = null,
    var gender: String? = null,
    var address: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var stdId: Int = 0
}