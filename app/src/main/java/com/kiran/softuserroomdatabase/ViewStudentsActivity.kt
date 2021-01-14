package com.kiran.softuserroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.softuserroomdatabase.adapter.StudentAdapter
import com.kiran.softuserroomdatabase.db.StudentDB
import com.kiran.softuserroomdatabase.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewStudentsActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        recyclerView = findViewById(R.id.recyclerView)

        CoroutineScope(Dispatchers.IO).launch {
            val lstStudents = StudentDB(this@ViewStudentsActivity).getStudentDAO().getAllStudents()

            withContext(Main){
                recyclerView.adapter = StudentAdapter(this@ViewStudentsActivity,lstStudents)
                recyclerView.layoutManager = LinearLayoutManager(this@ViewStudentsActivity)
            }
        }
    }
}
