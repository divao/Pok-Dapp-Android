package com.divao.pokedapp.presentation.common.navigation

import androidx.fragment.app.Fragment
import com.divao.pokedapp.presentation.common.MainContentFragment
import com.divao.pokedapp.presentation.scene.pokemon.detail.PokemonDetailFragment
import com.divao.pokedapp.presentation.scene.pokemon.list.PokemonListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen()

class PokemonListScreen : Screen() {
    override fun getFragment(): Fragment = PokemonListFragment.newInstance()
}

class PokemonDetailScreen(private val pokemonName: String) : Screen() {
    // TODO: adicionar parametro no newInstance
    override fun getFragment(): Fragment = PokemonDetailFragment.newInstance()
}

class MainContentScreen : Screen() {
    override fun getFragment(): Fragment = MainContentFragment.newInstance()
}