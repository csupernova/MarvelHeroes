package com.example.marvelheroes.data.network

import arrow.core.Either
import com.example.marvelheroes.data.network.models.InfoHeroes
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getHeroes(): Either<ApiError, InfoHeroes>
    @GET("v1/public/characters/{characterId}")
    suspend fun getInfoHero(@Path("characterId") characterId: String ): Either<ApiError, InfoHeroes>
}