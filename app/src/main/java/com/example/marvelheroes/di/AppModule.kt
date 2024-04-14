package com.example.marvelheroes.di

import android.app.Application
import androidx.room.Room
import com.example.marvelheroes.data.database.HeroDatabase
import com.example.marvelheroes.data.network.MarvelApi
import com.example.marvelheroes.data.network.MarvelApi.Companion.BASE_URL
import com.example.marvelheroes.data.network.eitherHandler.EitherCallAdapterFactory
import com.example.marvelheroes.data.network.interceptors.ParametersInterceptor
import com.example.marvelheroes.data.network.interceptors.logsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder()
        .addInterceptor(logsInterceptor)
        .addInterceptor(ParametersInterceptor())
        .build()

    @Provides
    @Singleton
    fun providersMarvelApi(): MarvelApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MarvelApi::class.java)
    }

    @Provides
    @Singleton
    fun providesHeroDatabase(app: Application): HeroDatabase {
        return Room.databaseBuilder(
            app,
            HeroDatabase::class.java,
            "marvelheroes.db"
        ).build()
    }
}