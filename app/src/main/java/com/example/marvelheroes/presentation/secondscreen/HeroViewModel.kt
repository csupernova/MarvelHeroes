package com.example.marvelheroes.presentation.secondscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.di.CharacterIdNavArg
import com.example.marvelheroes.domain.usecases.GetHeroUseCase
import com.example.marvelheroes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroUseCase: GetHeroUseCase,
    @CharacterIdNavArg private val characterIdArgument: String
): ViewModel() {

    var heroState = MutableStateFlow(HeroState())
        private set

    fun getHeroById(characterId: String = characterIdArgument) {
        viewModelScope.launch {
            getHeroUseCase.execute(characterId).collect {
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
