package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val dataSource: CharacterDataSource,
    private val mapper: CharacterMapper
) {

    suspend fun getPageOfCharacters(page: Int): Result<Page<Character>> {
        return dataSource.getCharacters(page).mapCatching { mapper.mapToPage(it) }
    }

    suspend fun getCharacter(id: Int): Result<Character> {
        return dataSource.getCharacter(id).mapCatching { mapper.mapToModel(it) }
    }
}
