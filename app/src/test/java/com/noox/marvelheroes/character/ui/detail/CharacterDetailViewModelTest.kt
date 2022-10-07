package com.noox.marvelheroes.character.ui.detail

import com.noox.marvelheroes.character.domain.usecase.GetCharacter
import com.noox.marvelheroes.character.ui.detail.CharacterDetailViewModel.UiState
import com.noox.marvelheroes.util.Constants
import com.noox.marvelheroes.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getCharacter = mockk<GetCharacter>()
    private val characterId = 1

    private val constants by lazy { Constants() }
    private val character = constants.character

    @Test
    fun `Should load character when view model is created` () = runTest {
        coEvery { getCharacter(characterId) } coAnswers { Result.success(character) }
        createViewModel()
        coVerify(exactly = 1) { getCharacter(characterId) }
    }

    @Test
    fun `Should set uiState as Loading and then as Success when load character is successful` () = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.success(character)
        }
        val viewModel = createViewModel()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Success)

        job.cancel()
    }

    @Test
    fun `Should set uiState as Loading and then as Error when load character failed` () = runTest {
        coEvery { getCharacter(characterId) } coAnswers {
            delay(10)
            Result.failure(Exception())
        }
        val viewModel = createViewModel()

        val uiStates = mutableListOf<UiState>()
        val job = launch(UnconfinedTestDispatcher()) { viewModel.uiState.toList(uiStates) }

        delay(20)

        Assert.assertEquals(2, uiStates.size)
        Assert.assertTrue(uiStates[0] is UiState.Loading)
        Assert.assertTrue(uiStates[1] is UiState.Error)

        job.cancel()
    }

    private fun createViewModel() = CharacterDetailViewModel(
        characterId = characterId,
        getCharacter = getCharacter
    )
}