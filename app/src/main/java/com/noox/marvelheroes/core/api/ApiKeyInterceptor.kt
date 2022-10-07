package com.noox.marvelheroes.core.api

import com.noox.marvelheroes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val publicApiKey = BuildConfig.PUBLIC_API_KEY
        val privateApiKey = BuildConfig.PRIVATE_API_KEY

        val hash = md5Hash(ts + privateApiKey + publicApiKey)

        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .addQueryParameter("apikey", publicApiKey)
            .build()
        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

    private fun md5Hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray()))
            .toString(16)
            .padStart(32, 'O')
    }
}
