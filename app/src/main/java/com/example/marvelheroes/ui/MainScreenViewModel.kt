package com.example.marvelheroes.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.network.MarvelApi
import com.example.marvelheroes.network.models.Hero
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainScreenViewModel: ViewModel() {
    var mainScreenUiState: MainScreenUiState by mutableStateOf(MainScreenUiState.Loading)
        private set

    init {
        getMarvelHeroes()
    }
    fun getMarvelHeroes() {
        viewModelScope.launch {
            mainScreenUiState = try {
                val result = MarvelApi.retrofitService.getHeroes()
                val listResult = mutableListOf<Hero>()
                for (elem in result.data.results) {
                    listResult.add(
                        Hero(
                            id = elem.id.toString(),
                            name = elem.name,
                            description = elem.description,
                            picture = "${elem.thumbnail.path}.${elem.thumbnail.extension}"
                        )
                    )
                }
            MainScreenUiState.Success(listResult)
            } catch (e: IOException) {
                MainScreenUiState.Error
            } catch (e: HttpException) {
                MainScreenUiState.Error
            }
        }
    }
}

sealed interface MainScreenUiState {
    data class Success(val heroes: List<Hero>): MainScreenUiState
    object Error: MainScreenUiState
    object Loading: MainScreenUiState
}