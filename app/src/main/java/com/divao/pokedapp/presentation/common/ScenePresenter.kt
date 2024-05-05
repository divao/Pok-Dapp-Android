package com.divao.pokedapp.presentation.common

import android.annotation.SuppressLint
import com.divao.domain.exception.NoInternetException
import com.divao.pokedapp.common.DisposableHolder
import com.divao.pokedapp.common.DisposableHolderDelegate
import io.reactivex.rxkotlin.addTo

@SuppressLint("CheckResult")
abstract class ScenePresenter(private val sceneView: SceneView) : DisposableHolder by DisposableHolderDelegate() {
    init {
        sceneView.onViewCreated.subscribe {
            handleViewCreation()
        }.addTo(disposables)
    }

    abstract fun handleViewCreation()

    fun handleGenericError(error: Throwable, isBlocking: Boolean = true) {
        when (error) {
            is NoInternetException -> if (isBlocking) sceneView.displayBlockingNoInternetError() else sceneView.displayNonBlockingNoInternetError()
            else -> if (isBlocking) sceneView.displayBlockingGenericError() else sceneView.displayNonBlockingGenericError()
        }
    }
}