package com.example.marvelheroes.ui.mainscreen

import com.example.marvelheroes.ui.Hero

data class HeroListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val heroList: List<Hero> = emptyList()
)
