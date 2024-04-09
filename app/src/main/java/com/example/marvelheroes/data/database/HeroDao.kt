package com.example.marvelheroes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface HeroDao {

    @Upsert
    suspend fun upsertHeroList(heroList: List<HeroEntity>)

    @Query("SELECT * FROM hero_table")
    fun getAllHeroes(): List<HeroEntity>

    @Query("SELECT * FROM hero_table WHERE id is :id")
    fun getHeroById(id: String): HeroEntity
}