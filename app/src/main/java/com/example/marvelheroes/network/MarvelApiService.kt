package com.example.marvelheroes.network

import com.example.marvelheroes.network.model.Root
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.Timestamp

private const val BASE_URL = "https://gateway.marvel.com"
private const val API_KEY = "d68f0a6174665d353025320a5bf55157&hash=01848ca6f19ecedaf1dc5161a5d9fa7b"
private const val TIMESTAMP = "1"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface MarvelApiService {
    @GET("v1/public/characters?ts=${TIMESTAMP}&apikey=${API_KEY}")
    suspend fun getHeroes(): Root
    @GET("v1/public/characters/{characterId}?ts=${TIMESTAMP}&apikey=${API_KEY}")
    suspend fun getInfoHero(@Path("characterId") characterId: String ): Root
}

object MarvelApi {
    val retrofitService:MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}