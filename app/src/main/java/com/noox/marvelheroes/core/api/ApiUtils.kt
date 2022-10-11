package com.noox.marvelheroes.core.api

import android.util.Log
import com.noox.marvelheroes.core.exception.NetworkException
import com.noox.marvelheroes.core.exception.UnexpectedException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <T> safeApiCall(call: () -> T): Result<T> {
    return try {
        Result.success(call())
    } catch (ex: Exception) {
        Log.e("ApiCall", "Call error: ${ex.localizedMessage}", ex.cause)
        when(ex) {
            is SocketTimeoutException -> Result.failure(NetworkException(ex))
            is UnknownHostException -> Result.failure(NetworkException(ex))
            is ConnectException -> Result.failure(NetworkException(ex))
            is HttpException -> Result.failure(NetworkException(ex))
            else -> Result.failure(UnexpectedException(ex))
        }
    }
}
