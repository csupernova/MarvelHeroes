package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.domain.repository.HeroListRepository
import com.example.marvelheroes.presentation.HeroUi
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHeroUseCase(private val heroListRepository: HeroListRepository) {
    suspend fun execute(id: String): Flow<Resource<HeroUi>> {
        return heroListRepository.getHero(id)
    }
}