package com.example.marvelheroes.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.domain.usecases.GetHeroListUseCase
import com.example.marvelheroes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroListUseCase: GetHeroListUseCase
): ViewModel() {

    var heroListState = MutableStateFlow(HeroListState())
        private set

    init {
        getMarvelHeroes()
    }

    private fun getMarvelHeroes() {
        viewModelScope.launch {
            heroListState.update {
                it.copy(isLoading = true)
            }
            getHeroListUseCase.execute(false).collect {
                    result ->
                when(result) {
                    is Resource.Error -> {
                        heroListState.update { it.copy(
                            isLoading = false,
                            isError = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let{ heroList ->
                            heroListState.update {
                                it.copy(
                                    isLoading = false,
                                    heroList = heroList)
                            }

                        }
                    }
                }
            }
        }
    }
}