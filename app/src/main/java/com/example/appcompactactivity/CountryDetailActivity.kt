package com.example.appcompactactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcompactactivity.databinding.ActivityCountryDetailBinding
import model.CountryInfo

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryInfo = intent.getSerializableExtra("country") as? CountryInfo

        if (countryInfo != null) {
            // Usa countryInfo para mostrar los detalles del país en la vista
            binding.nameTextView.text = countryInfo.nombre_pais
            binding.capitalTextView.text = countryInfo.capital
            binding.nameIntTextView.text = countryInfo.nombre_pais_int
            binding.siglaTextView.text = countryInfo.sigla

            loadFlagImage(countryInfo.sigla)
        } else {
        }
    }

    private fun loadFlagImage(sigla: String) {
        val flaResource = resources.getIdentifier("flag_${sigla.lowercase()}", "drawable", packageName)
        if (flaResource != 0) {
            binding.flagImageView.setImageResource(flaResource)
        } else {

        }
    }
}