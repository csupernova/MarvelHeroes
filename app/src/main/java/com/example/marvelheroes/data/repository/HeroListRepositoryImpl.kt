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

            val localHeroList = heroDatabase.heroDao().getAllHeroes()
            val shouldLoadLocalHero = localHeroList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalHero) {
                emit(Resource.Success(
                    data = localHeroList.map { hero -> hero.toHeroUi()}
                ))

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
        }
    }

    override suspend fun getHero(id: String): Flow<Resource<Hero>> {
        return flow {
            val localHeroList = heroDatabase.heroDao().getAllHeroes()

            val shouldLocalHero = localHeroList.isNotEmpty()
            if (shouldLocalHero) {
                val heroEntity = heroDatabase.heroDao().getHeroById(id)
                emit(Resource.Success(
                    data = heroEntity.toHeroUi()
                ))
                return@flow
            }
            lateinit var heroFromApi: HeroEntity
            marvelApi.getInfoHero(id).fold(
                ifLeft = { emit(Resource.Error(message = "Error")) },
                ifRight = {
                    it.data.results.map{ heroDto ->
                        heroFromApi = heroDto.toHeroEntity()
                    }
                }
            )
            heroDatabase.heroDao().upsertHero(heroFromApi)

            emit(Resource.Success(heroFromApi.toHeroUi()))
            return@flow
        }
    }
}