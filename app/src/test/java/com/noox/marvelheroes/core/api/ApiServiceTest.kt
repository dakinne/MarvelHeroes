package com.noox.marvelheroes.core.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class ApiServiceTest {

    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(path = "/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun loadCharacters() = runTest {
        val body = ClassLoader.getSystemResource("characters.json").readText()
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(body)
        )

        val characterDataWrapper = apiService.getCharacters(limit = 100, offset = 0)

        assertNotNull(characterDataWrapper)
    }

    @Test
    fun loadCharacter() = runTest {
        val body = ClassLoader.getSystemResource("character.json").readText()
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(body)
        )

        val characterDataWrapper = apiService.getCharacter(1)

        assertNotNull(characterDataWrapper)
    }
}
