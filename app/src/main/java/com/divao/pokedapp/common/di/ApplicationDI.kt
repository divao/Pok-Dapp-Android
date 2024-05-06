package com.divao.pokedapp.common.di

import android.content.Context
import android.util.Log
import com.divao.domain.di.BackgroundScheduler
import com.divao.domain.di.MainScheduler
import com.divao.domain.utility.Logger
import com.divao.pokedapp.data.remote.infrastructure.ErrorHandlingRxCallAdapterFactory
import com.pacoworks.rxpaper2.RxPaperBook
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance
                   @ApplicationContext applicationContext: Context): ApplicationComponent
    }

    @BackgroundScheduler
    fun backgroundScheduler(): Scheduler

    @MainScheduler
    fun mainScheduler(): Scheduler

    @ApplicationContext
    fun context(): Context

    fun logger(): Logger

    fun retrofit(): Retrofit

    fun rxPaperBook(): RxPaperBook
}

@Module
class ApplicationModule {

    @Provides
    @BackgroundScheduler
    fun backgroundScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @MainScheduler
    fun mainScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun logger() = object : Logger {
        override fun log(t: Throwable) {
            Log.e("Aplicou Exception: ", "", t)
        }
    }

    @Provides
    @Singleton
    fun retrofit() = Retrofit.Builder()
        .addCallAdapterFactory(ErrorHandlingRxCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pokeapi.co/api/v2/").build()

    @Provides
    @Singleton
    fun rxPaperBook(): RxPaperBook = RxPaperBook.with()
}