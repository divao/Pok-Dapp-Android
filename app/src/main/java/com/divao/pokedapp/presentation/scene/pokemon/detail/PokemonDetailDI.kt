package com.divao.pokedapp.presentation.scene.pokemon.detail

import com.divao.pokedapp.common.di.PerScene
import com.divao.pokedapp.presentation.common.navigation.FlowComponent
import dagger.BindsInstance
import dagger.Component

@PerScene
@Component(dependencies = [(FlowComponent::class)])
interface PokemonDetailComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance pokemonDetailView: PokemonDetailView,
                   flowComponent: FlowComponent
        ): PokemonDetailComponent
    }

    fun inject(pokemonDetailFragment: PokemonDetailFragment)
}