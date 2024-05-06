package com.divao.pokedapp.presentation.scene.pokemon.list

import com.divao.pokedapp.common.di.PerScene
import com.divao.pokedapp.presentation.common.navigation.FlowComponent
import dagger.BindsInstance
import dagger.Component

@PerScene
@Component(dependencies = [(FlowComponent::class)])
interface PokemonListComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance pokemonListView: PokemonListView,
                   flowComponent: FlowComponent): PokemonListComponent
    }

    fun inject(pokemonListFragment: PokemonListFragment)
}