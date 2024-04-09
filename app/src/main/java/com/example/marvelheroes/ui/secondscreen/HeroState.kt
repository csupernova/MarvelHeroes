package com.example.marvelheroes.ui.secondscreen

import com.example.marvelheroes.ui.Hero

data class HeroState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val hero: Hero? = null
)
