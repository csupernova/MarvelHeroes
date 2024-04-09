package com.example.marvelheroes.di

import com.example.marvelheroes.data.repository.HeroListRepository
import com.example.marvelheroes.data.repository.HeroListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHeroRepository(
        heroListRepositoryImpl: HeroListRepositoryImpl
    ): HeroListRepository
}