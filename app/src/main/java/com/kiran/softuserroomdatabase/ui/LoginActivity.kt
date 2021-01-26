package com.kiran.softuserroomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import com.google.android.material.textfield.TextInputEditText
import com.kiran.softuserroomdatabase.R
import com.kiran.softuserroomdatabase.db.StudentDB
import com.kiran.softuserroomdatabase.entity.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tvRegister: TextView
    private lateinit var btnLogin: Button
    private lateinit var chkRememberMe: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
        chkRememberMe = findViewById(R.id.chkRememberMe)

        btnLogin.setOnClickListener {
            login()
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterUserActivity::class.java))
        }
    }

    private suspend fun saveUserName() {
        val datastore = createDataStore(name = "username")
        val datastorekey = stringPreferencesKey("username")
        datastore.edit {
            it[datastorekey] = etUsername.text.toString()
        }
    }

    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        var user: User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = StudentDB
                .getInstance(this@LoginActivity)
                .getUserDAO()
                .checkUser(username, password)
            if (user == null) {
                withContext(Main) {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        DashboardActivity::class.java
                    )
                )
            }
        }

    }
}