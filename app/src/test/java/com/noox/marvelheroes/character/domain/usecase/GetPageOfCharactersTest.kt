package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page
import com.noox.marvelheroes.util.Constants
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPageOfCharactersTest {

    private val repository = mockk<CharacterRepository>()
    private val getPageOfCharacters = GetPageOfCharacters(repository = repository)

    private val pageNumber = 1

    private val constants by lazy { Constants() }
    private val page = constants.page0

    @Test
    fun `Should return Success when request page of characters is success` () = runTest {

        val expected = Result.success(page)
        coEvery { repository.getPageOfCharacters(pageNumber) } answers { expected }

        val result = getPageOfCharacters(pageNumber)

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request page of characters fail` () = runTest {

        val expected = Result.failure<Page<Character>>(Exception())
        coEvery { repository.getPageOfCharacters(pageNumber) } answers { expected }

        val result = getPageOfCharacters(pageNumber)

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(expected, result)
    }
}
