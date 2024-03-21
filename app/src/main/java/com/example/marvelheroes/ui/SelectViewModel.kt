package com.example.marvelheroes.ui

import androidx.lifecycle.ViewModel
import com.example.marvelheroes.data.SelectedCardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SelectedCardUiState())
    val uiState: StateFlow<SelectedCardUiState> = _uiState.asStateFlow()

    fun setCard(indexCard: Int, indexHero: String) {
        _uiState.update { currentState ->
            currentState.copy(
                indexSelected = indexCard,
                heroId = indexHero
            )
        }
    }
}