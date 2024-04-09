package com.example.marvelheroes.data.repository

import com.example.marvelheroes.data.database.HeroDatabase
import com.example.marvelheroes.data.database.HeroEntity
import com.example.marvelheroes.data.mappers.toHeroEntity
import com.example.marvelheroes.data.mappers.toHeroUi
import com.example.marvelheroes.data.network.MarvelApi
import com.example.marvelheroes.ui.Hero
import com.example.marvelheroes.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HeroListRepository {
    suspend fun getHeroList(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<Hero>>>

    suspend fun getHero(id: String): Flow<Resource<Hero>>
}

class HeroListRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi,
    private val heroDatabase: HeroDatabase
): HeroListRepository{
    override suspend fun getHeroList(forceFetchFromRemote: Boolean): Flow<Resource<List<Hero>>> {
        return flow {
            emit(Resource.Loading(true))

            val localHeroList = heroDatabase.heroDao().getAllHeroes()
            val shouldLoadLocalHero = localHeroList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalHero) {
                emit(Resource.Success(
                    data = localHeroList.map { hero -> hero.toHeroUi()}
                ))

                emit(Resource.Loading(false))
                return@flow
            }
            val heroListFromApi = mutableListOf<HeroEntity>()
            marvelApi.getHeroes().fold(
                ifLeft = { emit(Resource.Error(message = "Error")) },
                ifRight = {
                    it.data.results.map {heroDto ->
                        heroListFromApi.add(heroDto.toHeroEntity())
                    }
                }
            )
            heroDatabase.heroDao().upsertHeroList(heroListFromApi)

            emit(Resource.Success(
                heroListFromApi.map {it.toHeroUi()}
            ))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getHero(id: String): Flow<Resource<Hero>> {
        return flow {
            emit(Resource.Loading(true))

            val heroEntity = heroDatabase.heroDao().getHeroById(id)

            emit(Resource.Success(heroEntity.toHeroUi()))
            emit(Resource.Loading(false))
            return@flow
        }
    }

}