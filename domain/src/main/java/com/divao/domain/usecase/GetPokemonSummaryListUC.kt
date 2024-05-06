package com.divao.domain.usecase

import com.divao.domain.datarepository.PokemonDataRepository
import com.divao.domain.di.BackgroundScheduler
import com.divao.domain.di.MainScheduler
import com.divao.domain.model.PokemonSummary
import com.divao.domain.utility.Logger
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonSummaryListUC @Inject constructor(@BackgroundScheduler backgroundScheduler: Scheduler,
                                                  @MainScheduler mainScheduler: Scheduler,
                                                  logger: Logger,
                                                  private val pokemonRepository: PokemonDataRepository
) : SingleUseCase<List<PokemonSummary>, Unit>(backgroundScheduler, mainScheduler, logger) {
    override fun getRawSingle(params: Unit): Single<List<PokemonSummary>> = pokemonRepository.getPokemonSummaryList()
}