package com.divao.pokedapp.presentation.scene.pokemon.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.divao.pokedapp.R
import com.divao.pokedapp.databinding.FragmentMainFlowBinding
import com.divao.pokedapp.databinding.FragmentPokemonListBinding
import com.divao.pokedapp.presentation.common.SceneFragment
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class PokemonListFragment : SceneFragment(), PokemonListView {
    companion object {
        fun newInstance(): PokemonListFragment = PokemonListFragment()
    }

    private lateinit var pokemonListAdapter: PokemonListAdapter

    @Inject
    lateinit var pokemonListPresenter: PokemonListPresenter

    val component: PokemonListComponent by lazy {
        DaggerPokemonListComponent.factory().create(this, parentFlowContainerFragment!!.component)
    }

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    override val onTryAgain: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun displayPokemonSummaryList(pokemonSummaryList: List<PokemonSummaryVM>) {
        binding.pokemonListRecyclerView.visibility = View.VISIBLE
        binding.emptyStateLayout.root.visibility = View.GONE
        pokemonListAdapter.data = pokemonSummaryList
    }

    override fun displayBlockingGenericError() {
        binding.pokemonListRecyclerView.visibility = View.GONE
        binding.emptyStateLayout.root.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.fragmentToolbar.toolbarTitleTextView.text = getString(R.string.app_name)

        binding.emptyStateLayout.tryAgainButton.clicks().subscribe(onTryAgain)
    }

    private fun setupRecyclerView() {
        binding.pokemonListRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.pokemonListRecyclerView.adapter = pokemonListAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        pokemonListAdapter = PokemonListAdapter(context)
    }
}