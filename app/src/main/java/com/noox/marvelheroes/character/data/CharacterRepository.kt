package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page

class CharacterRepository(
    private val dataSource: CharacterDataSource,
    private val mapper: CharacterMapper
) {

    suspend fun getPageOfCharacters(page: Int): Result<Page<Character>> {
        return dataSource.getCharacters(page).mapCatching { mapper.mapToPage(it) }
    }

    suspend fun getCharacter(id: Int): Result<Character> {
        // TODO: What happend if mapper throws an exception
        return dataSource.getCharacter(id).mapCatching { mapper.mapToModel(it) }
    }
}
