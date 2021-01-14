package com.kiran.softuserroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }

        btnView.setOnClickListener {
            startActivity(Intent(this, ViewStudentsActivity::class.java))
        }
    }
}