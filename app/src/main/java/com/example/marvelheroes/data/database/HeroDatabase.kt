package com.example.marvelheroes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HeroEntity::class],
    version = 1
)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}