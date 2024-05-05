package com.divao.pokedapp.presentation.common.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.divao.pokedapp.presentation.common.EntryPointActivity
import com.divao.pokedapp.R
import com.divao.pokedapp.presentation.common.ExitHandler
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

sealed class FlowContainerFragment : Fragment() {
    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: PushNavigator

    abstract val initialScreen: Screen

    val component: FlowComponent by lazy {
        DaggerFlowComponent.factory()
            .create(childFragmentManager, R.id.sceneContainer, (activity as EntryPointActivity).component)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_navigation, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun onBackPressed(): Boolean {
        if (isAdded) {
            val childFragment = childFragmentManager.findFragmentById(R.id.sceneContainer)
            return if (childFragment?.parentFragment == initialScreen) {
                false
            } else {
                (childFragment as? ExitHandler)?.onBackPressed()
                true
            }
        }

        return false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isAdded) {
            childFragmentManager.findFragmentById(R.id.sceneContainer)?.let {
                if (it.isAdded) {
                    it.userVisibleHint = isVisibleToUser
                }
            }
        }
    }

    fun resetStack() {
        if (this::cicerone.isInitialized) {
            cicerone.router.backTo(initialScreen)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.sceneContainer) == null)
            cicerone.router.replaceScreen(initialScreen)
    }

    fun buildScreenStack(screenList: List<Screen>) {
        cicerone.router.newRootChain(*screenList.toTypedArray())
    }

    fun pushScreen(screen: Screen) {
        cicerone.router.navigateTo(screen)
    }

    fun popScreen() {
        cicerone.router.exit()
    }
}

class PokemonFlowContainerFragment : FlowContainerFragment() {
    override val initialScreen: Screen
        get() = PokemonListScreen()
}

class PokedexFlowContainerFragment : FlowContainerFragment() {
    override val initialScreen: Screen
        get() = PokemonListScreen()
}