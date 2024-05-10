package com.example.marvelheroes.domain.repository

import com.example.marvelheroes.ui.Hero
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HeroListRepository {
    suspend fun getHeroList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Hero>>>

    suspend fun getHero(id: String): Flow<Resource<Hero>>
}