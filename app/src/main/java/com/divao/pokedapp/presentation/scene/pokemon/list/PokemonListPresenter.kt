package com.divao.pokedapp.presentation.scene.pokemon.list

import com.divao.domain.usecase.GetPokemonSummaryListUC
import com.divao.pokedapp.presentation.common.ScenePresenter
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PokemonListPresenter @Inject constructor(
    private val view: PokemonListView,
    private val getPokemonSummaryList: GetPokemonSummaryListUC,
) : ScenePresenter(view) {
    override fun handleViewCreation() {
        Observable.merge(view.onViewResumed, view.onTryAgain)
            .doOnNext { view.displayLoading() }
            .flatMapCompletable {
                getPokemonSummaryList.getSingle(Unit)
                    .doOnSuccess { list ->
                        view.displayPokemonSummaryList(list.toVM())
                    }.doOnError { handleGenericError(it, true) }
                    .doFinally { view.dismissLoading() }
                    .ignoreElement()
                    .onErrorComplete()
            }.subscribe().addTo(view.disposables)
    }

}