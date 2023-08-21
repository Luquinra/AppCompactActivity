package com.example.appcompactactivity

import java.io.Serializable

data class CountryInfo(
        val capital: String,
        val nombre_pais: String,
        val nombre_pais_int: String,
        val sigla: String
    ) : Serializable