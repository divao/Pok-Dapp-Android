package com.divao.pokedapp.presentation.scene.pokemon.list

import android.content.Context
import android.widget.TextView
import androidx.compose.ui.text.capitalize
import com.bumptech.glide.Glide
import com.divao.pokedapp.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import java.util.Locale

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
            viewHolder.itemView.findViewById<TextView>(R.id.pokemonListNameTextView).text =
                pokemonVM.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }

        override fun getLayout(): Int = R.layout.item_pokemon_list_pokemon
    }

}