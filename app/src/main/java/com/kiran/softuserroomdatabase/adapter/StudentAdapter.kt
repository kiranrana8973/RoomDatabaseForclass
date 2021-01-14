package com.kiran.softuserroomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiran.softuserroomdatabase.R
import com.kiran.softuserroomdatabase.entity.Student

class StudentAdapter(
    private val context: Context,
    private val lstStudents: List<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFname: TextView= view.findViewById(R.id.tvFname)
        val tvLname: TextView= view.findViewById(R.id.tvLname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_student_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = lstStudents[position]
        holder.tvFname.text = student.fullName
       // holder.tvLname.text = student.lname
    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }
}