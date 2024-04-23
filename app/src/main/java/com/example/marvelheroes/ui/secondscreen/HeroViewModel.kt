package com.example.marvelheroes.ui.secondscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.repository.HeroListRepository
import com.example.marvelheroes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroListRepository: HeroListRepository
): ViewModel() {

    var heroState = MutableStateFlow(HeroState())
        private set

    fun getHeroById(characterId: String) {
        viewModelScope.launch {
            heroListRepository.getHero(characterId).collect {
                    result ->
                when(result) {
                    is Resource.Error -> {
                        heroState.update { it.copy(
                            isError = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let{ hero ->
                            heroState.update {
                                it.copy(
                                    hero = hero)
                            }

                        }
                    }
                }
            }
        }
    }
}
