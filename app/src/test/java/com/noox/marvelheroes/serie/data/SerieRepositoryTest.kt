package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.serie.domain.model.Serie
import com.noox.marvelheroes.util.Constants
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SerieRepositoryTest {

    private val dataSource = mockk<SerieDataSource>()
    private val mapper = mockk<SerieMapper>()
    private val repository = SerieRepository(dataSource, mapper)

    private val constants by lazy { Constants() }
    private val characterId = 1
    private val serie = constants.serie
    private val serieWrapper = constants.serieDataWrapper

    @Test
    fun `Should return Success when request series is success` () = runTest {

        coEvery { dataSource.getFirst20SeriesOfCharacter(characterId) } answers { Result.success(serieWrapper) }
        every { mapper.mapToList(serieWrapper) } returns listOf(serie)

        val expected = Result.success(listOf(serie))
        val result = repository.getFirst20SeriesOfCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when map fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20SeriesOfCharacter(characterId) } answers { Result.success(serieWrapper) }
        every { mapper.mapToList(serieWrapper) } throws exception

        val expected = Result.failure<Serie>(exception)
        val result = repository.getFirst20SeriesOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request series fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20SeriesOfCharacter(characterId) } answers { Result.failure(exception) }

        val expected = Result.failure<Serie>(exception)
        val result = repository.getFirst20SeriesOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }
}
