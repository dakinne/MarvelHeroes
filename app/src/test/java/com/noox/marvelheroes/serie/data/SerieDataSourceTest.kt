package com.noox.marvelheroes.serie.data

import android.util.Log
import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.exception.UnexpectedException
import com.noox.marvelheroes.serie.data.api.SerieDataSource
import com.noox.marvelheroes.serie.data.api.SerieDataWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SerieDataSourceTest {

    private val service = mockk<ApiService>()
    private val dataSource = SerieDataSource(service)

    private val characterId = 1
    private val data = SerieDataWrapper()

    @Test
    fun `Should return Success when request series is success` () = runTest {

        coEvery { service.getSeriesOfCharacter(characterId, any(), any()) } returns data

        val expected = Result.success(data)
        val result = dataSource.getFirst20SeriesOfCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request series throws an exception` () = runTest {

        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0

        coEvery { service.getSeriesOfCharacter(characterId, any(), any()) } throws Exception()

        val result = dataSource.getFirst20SeriesOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is UnexpectedException)
    }
}
