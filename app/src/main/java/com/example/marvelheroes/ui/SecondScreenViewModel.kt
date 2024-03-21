package com.example.marvelheroes.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.network.MarvelApi
import com.example.marvelheroes.network.models.Hero
import kotlinx.coroutines.launch
import java.io.IOException

class SecondScreenViewModel: ViewModel() {
    var secondScreenUiState: SecondScreenUiState by mutableStateOf(SecondScreenUiState.Loading)
        private set

    fun getMarvelInfoHero(characterId: String) {
        viewModelScope.launch {
            secondScreenUiState = try {
                val result = MarvelApi.retrofitService.getInfoHero(characterId)
                val hero = Hero(
                        id = result.data.results[0].id.toString(),
                        name = result.data.results[0].name,
                        description = result.data.results[0].description,
                        picture =
                        "${result.data.results[0].thumbnail.path}.${result.data.results[0].thumbnail.extension}"
                )
                SecondScreenUiState.Success(hero)
            } catch (e: IOException) {
                SecondScreenUiState.Error
            }
        }
    }
}

sealed interface SecondScreenUiState {
    data class Success(val hero: Hero): SecondScreenUiState
    object Error: SecondScreenUiState
    object Loading: SecondScreenUiState
}