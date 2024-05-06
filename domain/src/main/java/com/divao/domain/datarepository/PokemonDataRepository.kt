package com.divao.domain.datarepository

import com.divao.domain.model.PokemonDetail
import com.divao.domain.model.PokemonSummary
import io.reactivex.Single

interface PokemonDataRepository {
    fun getPokemonSummaryList(): Single<List<PokemonSummary>>
    fun getPokemonDetail(pokemonName: String): Single<PokemonDetail>
}