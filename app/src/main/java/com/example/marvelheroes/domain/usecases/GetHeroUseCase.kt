package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.domain.repository.HeroListRepository
import com.example.marvelheroes.ui.Hero
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHeroUseCase(private val heroListRepository: HeroListRepository) {
    suspend fun execute(id: String): Flow<Resource<Hero>> {
        return heroListRepository.getHero(id)
    }
}