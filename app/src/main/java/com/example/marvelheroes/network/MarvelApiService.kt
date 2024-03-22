package com.example.marvelheroes.network

import com.example.marvelheroes.network.interceptors.ParametersInterceptor
import com.example.marvelheroes.network.interceptors.logsInterceptor
import com.example.marvelheroes.network.models.GetHeroes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://gateway.marvel.com"

private val client = OkHttpClient.Builder()
    .addInterceptor(logsInterceptor)
    .addInterceptor(ParametersInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getHeroes(): GetHeroes
    @GET("v1/public/characters/{characterId}")
    suspend fun getInfoHero(@Path("characterId") characterId: String ): GetHeroes
}

object MarvelApi {
    val retrofitService:MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}