package com.divao.pokedapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetPokemonSummaryListResponseRM(
    @SerializedName("results")
    val results: List<PokemonSummaryRM>
)