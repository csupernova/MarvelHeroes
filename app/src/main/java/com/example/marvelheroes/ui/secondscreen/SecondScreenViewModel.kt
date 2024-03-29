package com.example.marvelheroes.ui.secondscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.network.ApiHeroesMapper
import com.example.marvelheroes.network.MarvelApi
import com.example.marvelheroes.ui.Hero
import kotlinx.coroutines.launch

class SecondScreenViewModel: ViewModel() {
    var secondScreenUiState: SecondScreenUiState by mutableStateOf(SecondScreenUiState.Loading)
        private set

    fun getMarvelInfoHero(characterId: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                MarvelApi.retrofitService.getInfoHero(characterId).fold (
                    ifLeft = { secondScreenUiState = SecondScreenUiState.Error},

                    ifRight = {hero ->
                        secondScreenUiState = SecondScreenUiState.Success(
                            ApiHeroesMapper.mapApiHeroToAppHero(hero.data.results[0])
                        )
                    }
                )
            }
        }
    }
}

sealed interface SecondScreenUiState {
    data class Success(val hero: Hero): SecondScreenUiState
    object Error: SecondScreenUiState
    object Loading: SecondScreenUiState
}