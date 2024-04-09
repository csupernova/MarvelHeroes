package com.example.marvelheroes.data.network

import com.example.marvelheroes.ui.Hero
import com.example.marvelheroes.data.network.models.Result

class ApiHeroesMapper {
    companion object {
        fun mapApiHeroesToAppHeroes(apiHeroes: List<Result>): List<Hero> {
            val listHeroes = mutableListOf<Hero>()
            for (elem in apiHeroes) {
                listHeroes.add(
                    Hero(
                        id = elem.id.toString(),
                        name = elem.name,
                        description = elem.description,
                        picture = "${elem.thumbnail.path}.${elem.thumbnail.extension}"
                    )
                )
            }
            return listHeroes
        }

        fun mapApiHeroToAppHero(apiHero: Result): Hero {
            return Hero(
                id = apiHero.id.toString(),
                name = apiHero.name,
                description = apiHero.description,
                picture = "${apiHero.thumbnail.path}.${apiHero.thumbnail.extension}"
            )
        }
    }
}