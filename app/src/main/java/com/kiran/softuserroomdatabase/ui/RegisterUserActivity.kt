package com.kiran.softuserroomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kiran.softuserroomdatabase.R
import com.kiran.softuserroomdatabase.db.StudentDB
import com.kiran.softuserroomdatabase.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var etFname: EditText
    private lateinit var etLname: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnAddStudent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        etFname = findViewById(R.id.etFname)
        etLname = findViewById(R.id.etLname)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnAddStudent = findViewById(R.id.btnAddStudent)

        btnAddStudent.setOnClickListener {
            val fname = etFname.text.toString()
            val lname = etLname.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                etPassword.error = "Password does not match"
                etPassword.requestFocus()
                return@setOnClickListener
            } else {
                val user = User(fname, lname, username, password)
                CoroutineScope(Dispatchers.IO).launch {
                    //StudentDB(this@RegisterUserActivity).getUserDAO().registerUser(user)
                    StudentDB.getInstance(this@RegisterUserActivity).getUserDAO().registerUser(user)
                }
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
            }
        }
    }
}