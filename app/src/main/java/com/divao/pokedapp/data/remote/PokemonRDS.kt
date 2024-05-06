package com.divao.pokedapp.data.remote

import com.divao.pokedapp.data.remote.model.GetPokemonSummaryListResponseRM
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonRDS {
    @GET("pokemon?limit=151")
    fun getPokemonSummaryList(): Single<GetPokemonSummaryListResponseRM>
}