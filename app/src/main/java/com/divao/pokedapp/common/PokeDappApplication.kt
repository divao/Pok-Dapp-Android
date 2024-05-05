package com.divao.pokedapp.common

import android.app.Application
import android.util.Log
import com.divao.pokedapp.common.di.ApplicationComponent
import com.divao.pokedapp.common.di.DaggerApplicationComponent
import com.evernote.android.state.StateSaver
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.plugins.RxJavaPlugins

class PokeDappApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        RxPaperBook.init(this)
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true)
        RxJavaPlugins.setErrorHandler { error ->
            Log.e("Unhandled Exception: ", "", error)
        }
    }
}