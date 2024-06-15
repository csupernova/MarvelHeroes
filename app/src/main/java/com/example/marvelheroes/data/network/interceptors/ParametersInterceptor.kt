package com.example.marvelheroes.data.network.interceptors

import com.example.marvelheroes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okio.HashingSource.Companion.md5
import java.security.MessageDigest


class ParametersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentUrl = chain.request().url
        val publicKey = BuildConfig.apiPublicKey
        val privateKey = BuildConfig.apiPrivateKey
        val ts = "1"
        val hash = "$ts$privateKey$publicKey".md5()
        val newUrl = currentUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("ts", ts)
            .build()
        val currentRequest = chain.request().newBuilder()
        val newRequest = currentRequest.url(newUrl).build()
        return chain.proceed(newRequest)
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}