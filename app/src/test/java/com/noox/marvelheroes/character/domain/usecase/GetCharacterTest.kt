package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.util.Constants
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharacterTest {

    private val repository = mockk<CharacterRepository>()
    private val getCharacter = GetCharacter(repository = repository)

    private val constants by lazy { Constants() }
    private val characterId = 1
    private val character = constants.character

    @Test
    fun `Should return Success when get character is success` () = runTest {

        coEvery { repository.getCharacter(characterId) } answers { Result.success(character) }

        val expected = Result.success(character)
        val result = getCharacter(characterId)

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when get character fail` () = runTest {

        val exception = Exception()
        coEvery { repository.getCharacter(characterId) } answers { Result.failure(exception) }

        val expected = Result.failure<Character>(exception)
        val result = getCharacter(characterId)

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(expected, result)
    }
}
