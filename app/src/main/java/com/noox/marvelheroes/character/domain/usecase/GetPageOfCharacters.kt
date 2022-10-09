package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPageOfCharacters(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int = 0): Result<Page<Character>> = withContext(dispatcher) {
        repository.getPageOfCharacters(page)
    }
}
