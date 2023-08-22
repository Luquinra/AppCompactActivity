package com.example.appcompactactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.appcompactactivity.databinding.ActivityCountriesBinding
import model.CountryInfo
import org.json.JSONException
import org.json.JSONObject

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding
    private lateinit var countriesList: List<CountryInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countriesList = readCountryInfoFromJson()

        setupListView(countriesList)
    }

    private fun readCountryInfoFromJson(): List<CountryInfo> {
        val countryInfoList = mutableListOf<CountryInfo>()
        val jsonString =
            resources.openRawResource(R.raw.paises).bufferedReader().use { it.readText() }

        try {
            val jsonObject = JSONObject(jsonString)
            val paisesArray = jsonObject.getJSONArray("paises")

            for (i in 0 until paisesArray.length()) {
                val paisObject = paisesArray.getJSONObject(i)
                val countryInfo = CountryInfo(
                    paisObject.getString("capital"),
                    paisObject.getString("nombre_pais"),
                    paisObject.getString("nombre_pais_int"),
                    paisObject.getString("sigla")
                )
                countryInfoList.add(countryInfo)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return countryInfoList
    }

    private fun setupListView(countriesList: List<CountryInfo>) {
        val listView: ListView = binding.listViewCountries

        val countryNamesList = countriesList.map { it.nombre_pais }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countryNamesList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCountryInfo = countriesList[position]

            val intent = Intent(this, CountryDetailActivity::class.java)
            intent.putExtra("country", selectedCountryInfo)
            startActivity(intent)
        }
    }
}

