package com.divao.pokedapp.data.mapper

import com.divao.domain.model.PokemonSummary
import com.divao.pokedapp.data.remote.model.PokemonSummaryRM
import com.divao.pokedapp.presentation.common.pokemonIdToImageUrl
import com.divao.pokedapp.presentation.common.pokemonUrlToId

fun List<PokemonSummaryRM>.toPokemonSummaryDM() = map { it.toDM() }
fun PokemonSummaryRM.toDM() = PokemonSummary(pokemonUrlToId(url), name, pokemonIdToImageUrl(pokemonUrlToId(url)))