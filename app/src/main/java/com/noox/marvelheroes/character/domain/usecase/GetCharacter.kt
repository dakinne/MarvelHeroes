package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacter(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): Result<Character> = withContext(dispatcher) {
        repository.getCharacter(characterId)
    }
}
