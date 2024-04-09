package com.example.marvelheroes.ui.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.network.ApiHeroesMapper
import com.example.marvelheroes.data.network.MarvelApi
import com.example.marvelheroes.ui.Hero
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    var mainScreenUiState: MainScreenUiState by mutableStateOf(MainScreenUiState.Loading)
        private set

    init {
        getMarvelHeroes()
    }
    fun getMarvelHeroes() {
        viewModelScope.launch {
            MarvelApi.retrofitService.getHeroes().fold (
                ifLeft = { mainScreenUiState = MainScreenUiState.Error},

                ifRight = {heroes ->
                    mainScreenUiState = MainScreenUiState.Success(
                        ApiHeroesMapper.mapApiHeroesToAppHeroes(heroes.data.results)
                    )
                }
            )
        }
    }
}

sealed interface MainScreenUiState {
    data class Success(val heroes: List<Hero>): MainScreenUiState
    object Error: MainScreenUiState
    object Loading: MainScreenUiState
}
