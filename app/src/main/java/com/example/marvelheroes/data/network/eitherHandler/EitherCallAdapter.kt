package com.example.marvelheroes.data.network.eitherHandler

import arrow.core.Either
import com.example.marvelheroes.data.network.ApiError
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
): CallAdapter<R, Call<Either<ApiError, R>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<R>): Call<Either<ApiError, R>> = EitherCall(call, successType)

}