package com.kiran.softuserroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        etUsername.setText("softwarica")
        etPassword.setText("coventry")

        btnLogin.setOnClickListener {
            if (etUsername.text?.trim().toString() == "softwarica" && etPassword.text?.trim()
                    .toString() == "coventry"
            ) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}