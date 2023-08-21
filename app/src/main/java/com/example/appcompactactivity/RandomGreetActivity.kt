package com.example.appcompactactivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RandomGreetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_greet)

        val language = intent.getStringExtra("language")
        val greeting = getGreetingForLanguage(language)

        val textViewGreeting = findViewById<TextView>(R.id.textGreeting)
        val imageViewFlag = findViewById<ImageView>(R.id.imageViewRG)
        textViewGreeting.text = greeting
        val flagResourceId = getFlagResourceForLanguage(language)

        setGreetingAndFlag(textViewGreeting, imageViewFlag, greeting, flagResourceId)
    }

    private fun setGreetingAndFlag(textViewGreeting: TextView, imageViewFlag: ImageView, greeting: String, flagResourceId: Int) {
        textViewGreeting.text = greeting
        imageViewFlag.setImageResource(flagResourceId)
    }

    private fun getFlagResourceForLanguage(language: String?): Int {
        return when (language) {
            "English" -> R.drawable.estados_unidos
            "Spanish" -> R.drawable.espa_a
            "French" -> R.drawable.francia
            "German" -> R.drawable.alemania
            else -> R.drawable.ping
        }
    }

    private fun getGreetingForLanguage(language: String?): String {
        // Implementa la lógica para obtener el saludo según el idioma
        return when (language) {
            "English" -> "Hello!"
            "Spanish" -> "¡Hola!"
            "French" -> "Bonjour !"
            "German" -> "Hallo!"
            else -> "Hello!" // Valor predeterminado en caso de idioma desconocido
        }
    }
}

