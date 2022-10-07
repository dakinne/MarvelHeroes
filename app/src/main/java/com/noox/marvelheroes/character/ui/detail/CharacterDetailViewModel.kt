package com.noox.marvelheroes.character.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.domain.usecase.GetCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterId: Int,
    private val getCharacter: GetCharacter
) : ViewModel() {

    sealed class UiState {
        data class Success(val character: Character): UiState()
        object Loading: UiState()
        object Error: UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        viewModelScope.launch {
            getCharacter(characterId).fold(
                onSuccess = { _uiState.emit(UiState.Success(it)) },
                onFailure = { _uiState.emit(UiState.Error) } // TODO: Improve error handler
            )
        }
    }

}
