package com.example.marvelheroes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_table")
data class HeroEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val picture: String
)