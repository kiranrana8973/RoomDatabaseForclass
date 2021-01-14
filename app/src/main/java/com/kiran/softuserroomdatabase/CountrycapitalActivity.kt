package com.kiran.softuserroomdatabase

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import java.io.*
import java.lang.Exception

class CountrycapitalActivity : AppCompatActivity() {

    private lateinit var etCountry: EditText
    private lateinit var etCapital: EditText
    private lateinit var btnAddCountry: Button
    private lateinit var lstCountries: ListView
    private var countryCapitalMap = mutableMapOf<String, String>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countrycapital)

        etCountry = findViewById(R.id.etCountry)
        etCapital = findViewById(R.id.etCapital)
        btnAddCountry = findViewById(R.id.btnAddCountry)
        lstCountries = findViewById(R.id.lstCountries)

        loadCountriesFromText()

        btnAddCountry.setOnClickListener {
            addCountryToText()
            loadCountriesFromText()
        }
    }

    private fun addCountryToText() {
        try {
            val country = etCountry.text.toString()
            val capital = etCapital.text.toString()
            val printStream = PrintStream(openFileOutput("Country.txt", MODE_APPEND))

            printStream.println("$country -> $capital")
            Toast.makeText(this, "$capital saved", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadCountriesFromText() {
        try {

            val fileInputStream = openFileInput("Country.txt")
            val inputStream = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStream)
            for (line in bufferedReader.lines()) {
                val countryCapital = line.split(" -> ")
                val country = countryCapital[0]
                val capital = countryCapital[1]
                countryCapitalMap.put(country, capital)
            }
            displayCountries(countryCapitalMap)

        } catch (e: IOException) {
            Toast.makeText(this, "Error ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayCountries(countryCapitalMap: MutableMap<String, String>) {
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            countryCapitalMap.keys.toTypedArray()
        )
        lstCountries.adapter = adapter
    }
}