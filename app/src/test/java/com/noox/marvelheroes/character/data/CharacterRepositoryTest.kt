package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
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
internal class CharacterRepositoryTest {

    private val dataSource = mockk<CharacterDataSource>()
    private val mapper = mockk<CharacterMapper>()
    private val repository = CharacterRepository(dataSource, mapper)

    private val constants by lazy { Constants() }
    private val characterId = 1
    private val character = constants.character
    private val characterWrapper = constants.characterDataWrapper

    // TODO: Add test for getCharacters

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
    fun `Should return Failure when map fail` () = runTest {

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