package com.divao.pokedapp.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.divao.pokedapp.R
import com.divao.pokedapp.databinding.FragmentMainFlowBinding
import com.divao.pokedapp.presentation.common.navigation.FlowContainerFragment
import com.divao.pokedapp.presentation.common.navigation.PokedexFlowContainerFragment
import com.divao.pokedapp.presentation.common.navigation.PokemonFlowContainerFragment
import com.divao.pokedapp.presentation.common.navigation.PokemonListScreen
import com.divao.pokedapp.presentation.common.navigation.Screen
import com.evernote.android.state.State

class MainContentFragment : Fragment(), ExitHandler {
    companion object {
        fun newInstance(): MainContentFragment = MainContentFragment()
    }

    private var _binding: FragmentMainFlowBinding? = null
    private val binding get() = _binding!!

    @State
    var currentTabName: String = PokemonFlowContainerFragment::class.java.simpleName

    private val pokemonFCFragment: PokemonFlowContainerFragment by lazy {
        childFragmentManager.findFragmentByTag(PokemonFlowContainerFragment::class.java.simpleName) as? PokemonFlowContainerFragment
            ?: PokemonFlowContainerFragment()
    }

    private val pokedexFCFragment: PokedexFlowContainerFragment by lazy {
        childFragmentManager.findFragmentByTag(PokedexFlowContainerFragment::class.java.simpleName) as? PokedexFlowContainerFragment
            ?: PokedexFlowContainerFragment()
    }

    private val currentFragment: FlowContainerFragment
        get() = childFragmentManager.findFragmentByTag(currentTabName) as? FlowContainerFragment
            ?: pokemonFCFragment


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigationBar()

        if (savedInstanceState == null) {
            changeFlow(pokemonFCFragment)
        }
    }

    private fun changeFlow(targetFragment: FlowContainerFragment) {
        with(childFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out)) {
            childFragmentManager.fragments.filter { it != targetFragment }.forEach {
                hide(it)
                it.userVisibleHint = false
            }

            currentTabName = targetFragment.javaClass.simpleName
            if (targetFragment.isAdded) {
                show(targetFragment)
            } else add(R.id.flowContainer, targetFragment, currentTabName)
            targetFragment.userVisibleHint = true  // avisa o fragment que ele esta visivel (ja que o onResume nao eh chamado quando dá .show())
            commit()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        currentFragment.userVisibleHint = isVisibleToUser
    }

    override fun onBackPressed() {
        currentFragment.onBackPressed()
    }

    private fun setupNavigationBar() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pokemon -> changeFlow(pokemonFCFragment)
                R.id.pokedex -> changeFlow(pokedexFCFragment)
            }
            true
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            currentFragment.resetStack()
        }
    }

    fun buildScreenStack(screenList: List<Screen>) {
        if (screenList.firstOrNull() is PokemonListScreen) {
            binding.bottomNavigationView.selectedItemId = R.id.pokemon
            pokemonFCFragment.buildScreenStack(screenList)
        } else {
            binding.bottomNavigationView.selectedItemId = R.id.pokedex
            pokedexFCFragment.buildScreenStack(screenList)
        }
    }
}