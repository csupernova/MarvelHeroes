package com.example.marvelheroes.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

val logsInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}