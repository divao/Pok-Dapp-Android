package com.divao.pokedapp.presentation.common

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.divao.pokedapp.common.di.ApplicationComponent
import com.divao.pokedapp.common.di.InnerContext
import com.divao.pokedapp.common.di.PerEntryPoint
import com.divao.pokedapp.presentation.common.navigation.PresentNavigator
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@PerEntryPoint
@Component(dependencies = [(ApplicationComponent::class)], modules = [EntryPointModule::class])
interface EntryPointComponent : ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance fragmentActivity: FragmentActivity,
                   @BindsInstance fragmentManager: FragmentManager,
                   @BindsInstance containerId: Int,
                   applicationComponent: ApplicationComponent): EntryPointComponent
    }

    @InnerContext
    fun innerContext(): Context

    fun presentNavigator(): PresentNavigator

    fun fragmentActivity(): FragmentActivity

    fun inject(entryPointActivity: EntryPointActivity)
}

@Module
class EntryPointModule {

    @Provides
    @PerEntryPoint
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @PerEntryPoint
    fun router(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    fun navigator(fragmentActivity: FragmentActivity, fragmentManager: FragmentManager, containerId: Int) = PresentNavigator(fragmentActivity, fragmentManager, containerId)

    @Provides
    @PerEntryPoint
    @InnerContext
    fun innerContext(fragmentActivity: FragmentActivity): Context = fragmentActivity
}