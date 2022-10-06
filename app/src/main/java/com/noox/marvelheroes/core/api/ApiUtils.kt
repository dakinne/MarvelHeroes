package com.noox.marvelheroes.core.api

import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

inline fun <T> safeApiCall(call: () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (ex: Exception) {
        Log.e("ApiCall", "Call error: ${ex.localizedMessage}", ex.cause)
        // TODO: Improve exceptions
        when(ex) {
            is SocketTimeoutException -> Result.failure(ex)
            is HttpException -> Result.failure(ex)
            is IOException -> Result.failure(ex)
            else -> Result.failure(ex)
        }
    }
}
