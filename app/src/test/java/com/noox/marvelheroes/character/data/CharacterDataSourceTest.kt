package com.noox.marvelheroes.character.data

import android.util.Log
import com.noox.marvelheroes.core.api.ApiService
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

    private val characterId = 1
    private val data = CharacterDataWrapper()

    // TODO: Add test for getCharacters

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

        val exception = Exception()
        coEvery { service.getCharacter(characterId) } throws exception

        val expected = Result.failure<CharacterDataWrapper>(exception)
        val result = dataSource.getCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }
}
