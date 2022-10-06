package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character

class CharacterRepository(
    private val dataSource: CharacterDataSource,
    private val mapper: CharacterMapper
) {

    // TODO: Add getCharacters method

    suspend fun getCharacter(id: Int): Result<Character> {
        // TODO: What happend if mapper throws an exception
        return dataSource.getCharacter(id).mapCatching {
            mapper.mapToModel(it).first()
        }
    }
}
