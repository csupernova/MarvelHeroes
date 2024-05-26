package com.example.marvelheroes.presentation.secondscreen

import com.example.marvelheroes.presentation.HeroUi

data class HeroState(
    val isError: Boolean = false,
    val hero: HeroUi? = null
)
