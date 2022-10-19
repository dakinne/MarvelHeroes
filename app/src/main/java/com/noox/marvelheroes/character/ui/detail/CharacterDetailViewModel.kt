package com.noox.marvelheroes.character.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.character.domain.usecase.GetCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacter: GetCharacter,
    private val state: SavedStateHandle
) : ViewModel() {

    private val characterId: Int by lazy { state.get<Int>(ARG_CHARACTER_ID)!! }

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
            _uiState.emit(UiState.Loading)
            getCharacter(characterId).fold(
                onSuccess = { _uiState.emit(UiState.Success(it)) },
                onFailure = { _uiState.emit(UiState.Error) }
            )
        }
    }

}
