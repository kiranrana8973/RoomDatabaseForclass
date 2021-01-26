package com.kiran.softuserroomdatabase.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.DiscretePathEffect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kiran.softuserroomdatabase.R
import com.kiran.softuserroomdatabase.db.StudentDB
import com.kiran.softuserroomdatabase.entity.Student
import com.kiran.softuserroomdatabase.ui.UpdateStudentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class StudentAdapter(
    private val context: Context,
    private val lstStudents: List<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFname: TextView = view.findViewById(R.id.tvFullName)
        val tvAge: TextView = view.findViewById(R.id.tvAge)
        val btnUpdate: ImageButton = view.findViewById(R.id.btnUpdate)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_student_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = lstStudents[position]
        holder.tvFname.text = student.fullName
        holder.tvAge.text = student.age.toString()

        holder.btnUpdate.setOnClickListener {
            val intent = Intent(context, UpdateStudentActivity::class.java)
            intent.putExtra("student",student)
            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${student.fullName} ??")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                deleteStudent(student)
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }


    private fun deleteStudent(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            StudentDB.getInstance(context).getStudentDAO()
                .DeleteStudent(student)
            withContext(Main) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }
}