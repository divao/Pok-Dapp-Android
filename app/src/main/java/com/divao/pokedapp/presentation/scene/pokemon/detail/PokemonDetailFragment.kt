package com.divao.pokedapp.presentation.scene.pokemon.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.divao.pokedapp.R
import com.divao.pokedapp.presentation.common.SceneFragment
import javax.inject.Inject

class PokemonDetailFragment : SceneFragment(), PokemonDetailView {
    companion object {
        fun newInstance(): PokemonDetailFragment = PokemonDetailFragment()
    }

    @Inject
    lateinit var pokemonDetailPresenter: PokemonDetailPresenter

    val component: PokemonDetailComponent by lazy {
        DaggerPokemonDetailComponent.factory().create(this, parentFlowContainerFragment!!.component)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO: criar e trocar para o layout do detail
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }
}