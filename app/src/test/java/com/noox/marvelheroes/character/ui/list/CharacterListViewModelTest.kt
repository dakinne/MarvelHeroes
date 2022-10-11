package com.noox.marvelheroes.character.ui.list

import com.noox.marvelheroes.character.domain.usecase.GetPageOfCharacters
import com.noox.marvelheroes.character.ui.list.CharacterListViewModel.UiState
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
class CharacterListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getPageOfCharacters = mockk<GetPageOfCharacters>()

    private val constants by lazy { Constants() }
    private val page = constants.page0

    @Test
    fun `Should load page 0 of characters when view model is created` () = runTest {
        coEvery { getPageOfCharacters(0) } coAnswers { Result.success(page) }
        createViewModel()
        coVerify(exactly = 1) { getPageOfCharacters(0) }
    }

    @Test
    fun `Should set uiState as Loading and then as Success when load page 0 of characters is successful` () = runTest {
        coEvery { getPageOfCharacters(0) } coAnswers {
            delay(10)
            Result.success(page)
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
    fun `Should set uiState as Loading and then as Error when load page 0 of characters failed` () = runTest {
        coEvery { getPageOfCharacters(0) } coAnswers {
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

    @Test
    fun `Should load page 1 when load next page is called after created viewModel` () = runTest {
        coEvery { getPageOfCharacters(0) } coAnswers { Result.success(page) }
        coEvery { getPageOfCharacters(1) } coAnswers { Result.success(page) }

        val viewModel = createViewModel()
        viewModel.loadNextPage()

        coVerify { getPageOfCharacters(1) }
    }

    @Test
    fun `Should load page 0 when try again is called after created viewModel` () = runTest {
        coEvery { getPageOfCharacters(0) } coAnswers { Result.failure(Exception()) }

        val viewModel = createViewModel()
        viewModel.tryAgain()

        coVerify { getPageOfCharacters(0) }
    }

    private fun createViewModel() = CharacterListViewModel(
        getPageOfCharacters = getPageOfCharacters
    )
}