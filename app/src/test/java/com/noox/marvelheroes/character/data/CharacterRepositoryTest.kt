package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page
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
class CharacterRepositoryTest {

    private val dataSource = mockk<CharacterDataSource>()
    private val mapper = mockk<CharacterMapper>()
    private val repository = CharacterRepository(dataSource, mapper)

    private val constants by lazy { Constants() }
    private val pageNumber = 1
    private val characterId = 1
    private val character = constants.character
    private val characterWrapper = constants.characterDataWrapper

    @Test
    fun `Should return Success when request characters is success` () = runTest {

        val page = Page(listOf(character), 1, 100)

        coEvery { dataSource.getCharacters(pageNumber) } answers { Result.success(characterWrapper) }
        every { mapper.mapToPage(characterWrapper) } returns page

        val expected = Result.success(page)
        val result = repository.getPageOfCharacters(pageNumber)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when map to page fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getCharacters(pageNumber) } answers { Result.success(characterWrapper) }
        every { mapper.mapToPage(characterWrapper) } throws exception

        val expected = Result.failure<Character>(exception)
        val result = repository.getPageOfCharacters(pageNumber)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request characters fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getCharacters(pageNumber) } answers { Result.failure(exception) }

        val expected = Result.failure<Character>(exception)
        val result = repository.getPageOfCharacters(pageNumber)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Success when request character is success` () = runTest {

        coEvery { dataSource.getCharacter(characterId) } answers { Result.success(characterWrapper) }
        every { mapper.mapToModel(characterWrapper) } returns character

        val expected = Result.success(character)
        val result = repository.getCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when map to model fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getCharacter(characterId) } answers { Result.success(characterWrapper) }
        every { mapper.mapToModel(characterWrapper) } throws exception

        val expected = Result.failure<Character>(exception)
        val result = repository.getCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request character fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getCharacter(characterId) } answers { Result.failure(exception) }

        val expected = Result.failure<Character>(exception)
        val result = repository.getCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }
}
