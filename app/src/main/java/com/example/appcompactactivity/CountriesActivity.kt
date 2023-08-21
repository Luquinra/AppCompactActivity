package com.example.appcompactactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.appcompactactivity.databinding.ActivityCountriesBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding
    private lateinit var countriesList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countriesList = readCountriesFromJson()

        setupListView(countriesList)
    }

    private fun readCountriesFromJson(): List<String> {
        val countriesList = mutableListOf<String>()
        val jsonString =
            resources.openRawResource(R.raw.paises).bufferedReader().use { it.readText() }

        try {
            val jsonObject = JSONObject(jsonString)
            val paisesArray = jsonObject.getJSONArray("paises")

            for (i in 0 until paisesArray.length()) {
                val paisObject = paisesArray.getJSONObject(i)
                val nombrePais = paisObject.getString("nombre_pais")
                countriesList.add(nombrePais)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return countriesList
    }

    private fun setupListView(countriesList: List<String>) {
        val listView: ListView = binding.listViewCountries
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countriesList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCountryName = countriesList[position]
            val selectedCountryInfo = getCountryInfoFromName(selectedCountryName)

            if (selectedCountryInfo != null) {
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("country", selectedCountryInfo)
                startActivity(intent)
            } else {
                // Manejar caso de país no encontrado
            }
        }
    }

    private fun getCountryInfoFromName(countryName: String): CountryInfo? {
        val jsonString =
            resources.openRawResource(R.raw.paises).bufferedReader().use { it.readText() }

        try {
            val jsonObject = JSONObject(jsonString)
            val paisesArray = jsonObject.getJSONArray("paises")

            for (i in 0 until paisesArray.length()) {
                val paisObject = paisesArray.getJSONObject(i)
                val nombrePais = paisObject.getString("nombre_pais")

                if (nombrePais == countryName) {
                    return CountryInfo(
                        paisObject.getString("capital"),
                        paisObject.getString("nombre_pais"),
                        paisObject.getString("nombre_pais_int"),
                        paisObject.getString("sigla")
                    )
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }
}








