package com.example.marvelheroes.data.mappers

import com.example.marvelheroes.data.database.HeroEntity
import com.example.marvelheroes.data.network.models.HeroDto
import com.example.marvelheroes.ui.Hero

fun HeroDto.toHeroUi(): Hero {
    return Hero(
        id = id.toString(),
        name = name,
        description = description,
        picture = "${thumbnail.path}.${thumbnail.extension}"
    )
}

fun HeroEntity.toHeroUi(): Hero {
    return Hero(
        id = id,
        name = name,
        description = description,
        picture = picture
    )
}

fun HeroDto.toHeroEntity(): HeroEntity {
    return HeroEntity(
        id = id.toString(),
        name = name,
        description = description,
        picture = "${thumbnail.path}.${thumbnail.extension}"
    )
}