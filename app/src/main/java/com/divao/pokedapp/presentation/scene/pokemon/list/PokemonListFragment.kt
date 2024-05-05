package com.divao.pokedapp.presentation.scene.pokemon.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.divao.pokedapp.R
import com.divao.pokedapp.presentation.common.SceneFragment
import javax.inject.Inject

class PokemonListFragment : SceneFragment(), PokemonListView {
    companion object {
        fun newInstance(): PokemonListFragment = PokemonListFragment()
    }

    @Inject
    lateinit var pokemonListPresenter: PokemonListPresenter

    val component: PokemonListComponent by lazy {
        DaggerPokemonListComponent.factory().create(this, parentFlowContainerFragment!!.component)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }
}