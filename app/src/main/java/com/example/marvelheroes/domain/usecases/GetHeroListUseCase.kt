package com.example.marvelheroes.domain.usecases

import com.example.marvelheroes.domain.repository.HeroListRepository
import com.example.marvelheroes.presentation.HeroUi
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHeroListUseCase(private val heroListRepository: HeroListRepository) {
    suspend fun execute(forceFetch: Boolean): Flow<Resource<List<HeroUi>>> {
        return heroListRepository.getHeroList(forceFetch)
    }
}