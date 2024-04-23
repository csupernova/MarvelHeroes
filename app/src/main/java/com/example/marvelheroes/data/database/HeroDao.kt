package com.example.marvelheroes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface HeroDao {

    @Upsert
    suspend fun upsertHeroList(heroList: List<HeroEntity>)

    @Upsert
    suspend fun upsertHero(hero: HeroEntity)

    @Query("SELECT * FROM hero_table")
    suspend fun getAllHeroes(): List<HeroEntity>

    @Query("SELECT * FROM hero_table WHERE id is :id")
    suspend fun getHeroById(id: String): HeroEntity
}