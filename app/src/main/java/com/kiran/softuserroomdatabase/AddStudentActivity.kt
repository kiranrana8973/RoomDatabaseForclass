package com.kiran.softuserroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kiran.softuserroomdatabase.db.StudentDB
import com.kiran.softuserroomdatabase.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val student = Student("Kiran","Rana","kiran","kiran")
        CoroutineScope(Dispatchers.IO).launch {
            StudentDB(this@AddStudentActivity).getStudentDAO().insertStudent(student)
        }
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
    }
}