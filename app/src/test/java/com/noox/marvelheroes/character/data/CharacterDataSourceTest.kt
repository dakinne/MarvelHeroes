package com.noox.marvelheroes.character.data

import android.util.Log
import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.exception.UnexpectedException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CharacterDataSourceTest {

    private val service = mockk<ApiService>()
    private val dataSource = CharacterDataSource(service)

    private val page = 1
    private val characterId = 1
    private val data = CharacterDataWrapper()

    @Test
    fun `Should return Success when request characters is success` () = runTest {

        coEvery { service.getCharacters(any(), any()) } returns data

        val expected = Result.success(data)
        val result = dataSource.getCharacters(page)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request characters throws an exception` () = runTest {

        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0

        coEvery { service.getCharacters(any(), any()) } throws Exception()

        val result = dataSource.getCharacters(page)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is UnexpectedException)
    }

    @Test
    fun `Should return Success when request character is success` () = runTest {

        coEvery { service.getCharacter(characterId) } returns data

        val expected = Result.success(data)
        val result = dataSource.getCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request character throws an exception` () = runTest {

        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0

        coEvery { service.getCharacter(characterId) } throws Exception()

        val result = dataSource.getCharacter(characterId)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is UnexpectedException)
    }
}
