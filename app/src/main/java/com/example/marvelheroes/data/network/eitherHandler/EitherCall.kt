package com.example.marvelheroes.data.network.eitherHandler

import arrow.core.Either
import com.example.marvelheroes.data.network.ApiError
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.example.marvelheroes.data.network.ApiError.HttpError
import com.example.marvelheroes.data.network.ApiError.UnknownApiError
import okio.IOException


class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
): Call<Either<ApiError, R>> {
    override fun enqueue(callback: Callback<Either<ApiError, R>>) = delegate.enqueue(
        object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }
            private fun Response<R>.toEither(): Either<ApiError, R> {
                if (!isSuccessful) {
                    val errorBody = errorBody()?.string() ?: ""
                    return Left(HttpError(code(), errorBody))
                }
                body()?.let {body -> return Right(body) }

                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Right(Unit) as Either<ApiError, R>
                }
                else {
                    @Suppress("UNCHECKED_CAST")
                    Left(UnknownError("Response body is null")) as Either<ApiError, R>
                }
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                val error = when  (t) {
                    is IOException -> ApiError.NetworkError(t)
                    else -> UnknownApiError(t)
                }
                callback.onResponse(this@EitherCall, Response.success(Left(error)))
            }

        }
    )

    override fun cancel() = delegate.cancel()
    override fun clone(): Call<Either<ApiError, R>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<Either<ApiError, R>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }


}