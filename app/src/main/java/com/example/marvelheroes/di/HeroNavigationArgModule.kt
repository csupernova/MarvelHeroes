package com.example.marvelheroes.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier




@Qualifier
annotation class CharacterIdNavArg
@Module
@InstallIn(ViewModelComponent::class)
object HeroNavigationArgModule {

    @Provides
    @CharacterIdNavArg
    @ViewModelScoped
    fun provideCharacterIdNavArg(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle.get<String>("characterId") ?: ""
    }
}