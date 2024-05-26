package com.example.marvelheroes.data.mappers

import com.example.marvelheroes.data.database.HeroEntity
import com.example.marvelheroes.data.network.models.HeroDto
import com.example.marvelheroes.presentation.HeroUi

fun HeroDto.toHeroUi(): HeroUi {
    return HeroUi(
        id = id.toString(),
        name = name,
        description = description,
        picture = "${thumbnail.path}.${thumbnail.extension}"
    )
}

fun HeroEntity.toHeroUi(): HeroUi {
    return HeroUi(
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