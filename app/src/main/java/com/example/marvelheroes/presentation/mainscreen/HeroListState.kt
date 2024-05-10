package com.example.marvelheroes.presentation.mainscreen

import com.example.marvelheroes.presentation.HeroUi

data class HeroListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val heroList: List<HeroUi> = emptyList()
)
