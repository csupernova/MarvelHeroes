package com.example.marvelheroes.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response


class ParametersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentUrl = chain.request().url
        val newUrl = currentUrl.newBuilder()
            .addQueryParameter("apikey", "d68f0a6174665d353025320a5bf55157")
            .addQueryParameter("hash", "01848ca6f19ecedaf1dc5161a5d9fa7b")
            .addQueryParameter("ts", "1")
            .build()
        val currentRequest = chain.request().newBuilder()
        val newRequest = currentRequest.url(newUrl).build()
        return chain.proceed(newRequest)
    }
}