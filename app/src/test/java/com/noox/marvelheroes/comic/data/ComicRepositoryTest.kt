package com.noox.marvelheroes.comic.data

import com.noox.marvelheroes.comic.domain.model.Comic
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
internal class ComicRepositoryTest {

    private val dataSource = mockk<ComicDataSource>()
    private val mapper = mockk<ComicMapper>()
    private val repository = ComicRepository(dataSource, mapper)

    private val constants by lazy { Constants() }
    private val characterId = 1
    private val comic = constants.comic
    private val comicWrapper = constants.comicDataWrapper

    @Test
    fun `Should return Success when request comics is success` () = runTest {

        coEvery { dataSource.getFirst20ComicsOfCharacter(characterId) } answers { Result.success(comicWrapper) }
        every { mapper.mapToList(comicWrapper) } returns listOf(comic)

        val expected = Result.success(listOf(comic))
        val result = repository.getFirst20ComicsOfCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when map fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20ComicsOfCharacter(characterId) } answers { Result.success(comicWrapper) }
        every { mapper.mapToList(comicWrapper) } throws exception

        val expected = Result.failure<Comic>(exception)
        val result = repository.getFirst20ComicsOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request comics fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20ComicsOfCharacter(characterId) } answers { Result.failure(exception) }

        val expected = Result.failure<Comic>(exception)
        val result = repository.getFirst20ComicsOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }
}
