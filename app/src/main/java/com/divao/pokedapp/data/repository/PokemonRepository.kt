package com.divao.pokedapp.data.repository

import com.divao.domain.datarepository.PokemonDataRepository
import com.divao.domain.model.PokemonDetail
import com.divao.domain.model.PokemonSummary
import com.divao.pokedapp.data.cache.PokemonCDS
import com.divao.pokedapp.data.remote.PokemonRDS
import com.divao.pokedapp.data.mapper.toPokemonSummaryDM
import io.reactivex.Single
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonRDS: PokemonRDS,
    private val pokemonCDS: PokemonCDS
) : PokemonDataRepository {
    override fun getPokemonSummaryList(): Single<List<PokemonSummary>> =
        pokemonRDS.getPokemonSummaryList().map { response -> response.results.toPokemonSummaryDM() }

    override fun getPokemonDetail(pokemonName: String): Single<PokemonDetail> {
        TODO("Not yet implemented")
    }

}