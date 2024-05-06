package com.divao.pokedapp.presentation.common.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.divao.domain.datarepository.PokemonDataRepository
import com.divao.pokedapp.common.di.PerFlow
import com.divao.pokedapp.data.remote.PokemonRDS
import com.divao.pokedapp.data.repository.PokemonRepository
import com.divao.pokedapp.presentation.common.EntryPointComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class FlowModule {
    @Provides
    @PerFlow
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @PerFlow
    fun router(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @PerFlow
    fun navigator(fragmentActivity: FragmentActivity, fragmentManager: FragmentManager, containerId: Int): PushNavigator = PushNavigator(fragmentActivity, fragmentManager, containerId)

    @Provides
    @PerFlow
    fun pokemonRDS(retrofit: Retrofit): PokemonRDS = retrofit.create(PokemonRDS::class.java)

    @Provides
    @PerFlow
    fun pokemonDataRepository(pokemonRepository: PokemonRepository): PokemonDataRepository = pokemonRepository
}

@PerFlow
@Component(dependencies = [(EntryPointComponent::class)], modules = [(FlowModule::class)])
interface FlowComponent : EntryPointComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance fm: FragmentManager,
                   @BindsInstance containerId: Int,
                   entryPointComponent: EntryPointComponent): FlowComponent
    }

    fun inject(flowContainerFragment: FlowContainerFragment)
    fun pokemonDataRepository(): PokemonDataRepository
}