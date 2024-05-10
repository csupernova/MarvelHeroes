package com.example.marvelheroes.di

import com.example.marvelheroes.domain.repository.HeroListRepository
import com.example.marvelheroes.domain.usecases.GetHeroListUseCase
import com.example.marvelheroes.domain.usecases.GetHeroUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetHeroListUseCase(
        heroListRepository: HeroListRepository
    ): GetHeroListUseCase {
        return GetHeroListUseCase(heroListRepository)
    }

    @Provides
    @Singleton
    fun provideGetHeroUseCase(
        heroListRepository: HeroListRepository
    ): GetHeroUseCase {
        return GetHeroUseCase(heroListRepository)
    }
}