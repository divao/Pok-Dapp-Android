package com.divao.pokedapp.presentation.scene.pokemon.list

import com.divao.domain.model.PokemonSummary

fun List<PokemonSummary>.toVM(): List<PokemonSummaryVM> = map { it.toVM() }

fun PokemonSummary.toVM(): PokemonSummaryVM = PokemonSummaryVM(id, name, imageUrl)