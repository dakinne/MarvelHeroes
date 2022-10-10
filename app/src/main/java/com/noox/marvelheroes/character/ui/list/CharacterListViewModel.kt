package com.noox.marvelheroes.character.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.domain.usecase.GetPageOfCharacters
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getPageOfCharacters: GetPageOfCharacters
) : ViewModel() {

    sealed class UiState {
        data class Success(val characters: List<Character>, val hasMorePages: Boolean): UiState()
        object Loading: UiState()
        object Error: UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private var nextPage = 0
    private val characters = mutableListOf<Character>()

    private var loadJob: Job? = null

    init {
        loadCharacters()
    }

    fun tryAgain() {
        _uiState.value = UiState.Loading
        loadNextPage()
    }

    fun loadNextPage() {
        if (loadJob?.isCompleted == true) {
            loadCharacters(nextPage)
        }
    }

    private fun loadCharacters(page: Int = 0) {
        loadJob = viewModelScope.launch {
            getPageOfCharacters(page).fold(
                onSuccess = { pageOfCharacters ->
                    characters.addAll(pageOfCharacters.items)
                    _uiState.value = UiState.Success(characters.toList(), pageOfCharacters.hasMorePages)
                    nextPage = pageOfCharacters.currentPage + 1
                },
                onFailure = { _uiState.value = UiState.Error }
            )
        }
    }

}
