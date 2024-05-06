package com.divao.pokedapp.presentation.scene.pokemon.list

import android.content.Context
import com.bumptech.glide.Glide
import com.divao.pokedapp.R
import com.divao.pokedapp.databinding.ItemPokemonListPokemonBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem

class PokemonListAdapter(private val context: Context?) : GroupAdapter<GroupieViewHolder>() {

    var data: List<PokemonSummaryVM> = emptyList()
        set(pokemon) {
            field = pokemon
            clear()
            pokemon.forEach { add(PokemonItem(it)) }
        }

    private inner class PokemonItem(private val pokemonVM: PokemonSummaryVM) :
        Item<GroupieViewHolder>() {

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            context?.let {
                Glide.with(it)
                    .load(pokemonVM.imageURL)
                    .into(viewHolder.itemView.findViewById(R.id.pokemonListImageView))
            }
        }

        override fun getLayout(): Int = R.layout.item_pokemon_list_pokemon
    }

}