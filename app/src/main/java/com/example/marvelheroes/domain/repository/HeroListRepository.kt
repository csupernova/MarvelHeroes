package com.example.marvelheroes.domain.repository

import com.example.marvelheroes.presentation.HeroUi
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HeroListRepository {
    suspend fun getHeroList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<HeroUi>>>

    suspend fun getHero(id: String): Flow<Resource<HeroUi>>
}