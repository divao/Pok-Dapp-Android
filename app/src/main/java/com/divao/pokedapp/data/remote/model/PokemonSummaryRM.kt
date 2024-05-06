package com.divao.pokedapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonSummaryRM(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)