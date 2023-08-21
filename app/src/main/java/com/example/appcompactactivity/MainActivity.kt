package com.example.appcompactactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.appcompactactivity.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spinnerGreeting: Spinner
    private lateinit var selectedLanguage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTicTacToe.setOnClickListener {
            startActivity(Intent(baseContext, TicTacToeActivity::class.java))
        }
        binding.buttonRandomGreet.setOnClickListener {
            startActivity(Intent(baseContext, RandomGreetActivity::class.java))
        }
        binding.buttonCountries.setOnClickListener {
            startActivity(Intent(baseContext, CountriesActivity::class.java))
        }
        setupSpinner()
        setupRandomGreetButton()
        }

    private fun setupSpinner() {
        spinnerGreeting = findViewById(R.id.spinner)

        val languages = listOf("English", "Spanish", "French", "German") // Aquí proporciona la lista de idiomas
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerGreeting.adapter = adapter


        spinnerGreeting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedLanguage = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupRandomGreetButton() {
        val randomGreetButton = findViewById<Button>(R.id.buttonRandomGreet)
        randomGreetButton.setOnClickListener {
            startRandomGreetActivity()
        }
    }

    private fun startRandomGreetActivity() {
        val intent = Intent(this, RandomGreetActivity::class.java)
        intent.putExtra("language", selectedLanguage)
        startActivity(intent)
    }
}



