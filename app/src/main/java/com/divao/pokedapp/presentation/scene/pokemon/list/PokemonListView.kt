package com.divao.pokedapp.presentation.scene.pokemon.list

import com.divao.pokedapp.presentation.common.SceneView
import io.reactivex.Observable

interface PokemonListView : SceneView {
    val onTryAgain: Observable<Unit>

    fun displayPokemonSummaryList(pokemonSummaryList: List<PokemonSummaryVM>)
}