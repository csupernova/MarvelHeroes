package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.domain.repository.HeroListRepository
import com.example.marvelheroes.ui.Hero
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHeroListUseCase(private val heroListRepository: HeroListRepository) {
    suspend fun execute(forceFetch: Boolean): Flow<Resource<List<Hero>>> {
        return heroListRepository.getHeroList(forceFetch)
    }
}