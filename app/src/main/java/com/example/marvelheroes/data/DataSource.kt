package com.example.marvelheroes.data

import com.example.marvelheroes.R

data class InfoHeroes(
    val name: Int,
    val description: Int,
    val picture: Int
)
object DataSource {
    val heroes: List<InfoHeroes> = listOf(
        InfoHeroes(R.string.deadpool, R.string.deadpool_desc, R.string.deadpool_url ),
        InfoHeroes(R.string.iron_man, R.string.iron_man_desc, R.string.iron_man_url),
        InfoHeroes(R.string.spiderman, R.string.spiderman_desc, R.string.spiderman_url)
    )
}