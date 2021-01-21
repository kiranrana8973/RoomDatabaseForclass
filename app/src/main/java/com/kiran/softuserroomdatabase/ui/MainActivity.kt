package com.kiran.softuserroomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kiran.softuserroomdatabase.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnRoomDatabase : Button
    private lateinit var btnCountryCapital : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRoomDatabase = findViewById(R.id.btnRoomDatabase)
        btnCountryCapital = findViewById(R.id.btnCountryCapital)

        btnRoomDatabase.setOnClickListener {
            startActivity(Intent(this@MainActivity, SplashScreenActivity::class.java))
        }

        btnCountryCapital.setOnClickListener {
            startActivity(Intent(this@MainActivity, CountrycapitalActivity::class.java))
        }
    }
}