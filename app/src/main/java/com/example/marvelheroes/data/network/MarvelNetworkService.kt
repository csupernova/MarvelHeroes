package com.example.marvelheroes.data.network

import com.example.marvelheroes.data.network.eitherHandler.EitherCallAdapterFactory
import com.example.marvelheroes.data.network.interceptors.ParametersInterceptor
import com.example.marvelheroes.data.network.interceptors.logsInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://gateway.marvel.com"

private val client = OkHttpClient.Builder()
    .addInterceptor(logsInterceptor)
    .addInterceptor(ParametersInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(EitherCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object MarvelApi {
    val retrofitService: MarvelApiService by lazy {
        retrofit.create(MarvelApiService::class.java)
    }
}

sealed class ApiError {
    data class HttpError(val code: Int, val body: String): ApiError()

    data class NetworkError(val throwable: Throwable): ApiError()

    data class UnknownApiError(val throwable: Throwable): ApiError()
}