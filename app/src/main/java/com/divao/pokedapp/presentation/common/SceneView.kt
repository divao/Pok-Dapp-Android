package com.divao.pokedapp.presentation.common

import com.divao.pokedapp.common.DisposableHolder
import io.reactivex.Observable

interface SceneView : DisposableHolder {
    val onViewLoaded: Observable<Unit>
    val onViewCreated: Observable<Unit>
    val onViewResumed: Observable<Unit>
    val onViewPaused: Observable<Unit>
    val onViewDestroyed: Observable<Unit>

    fun displayLoading()
    fun dismissLoading()
    fun displayBlockingGenericError()
    fun displayNonBlockingGenericError()
    fun displayBlockingNoInternetError()
    fun displayNonBlockingNoInternetError()
}