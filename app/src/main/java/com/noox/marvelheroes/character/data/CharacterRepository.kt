package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.data.api.CharacterDataSource
import com.noox.marvelheroes.character.data.cache.CharacterDao
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page

class CharacterRepository(
    private val dataSource: CharacterDataSource,
    private val mapper: CharacterMapper,
    private val dao: CharacterDao
) {

    suspend fun getPageOfCharacters(page: Int): Result<Page<Character>> {
        return dataSource.getCharacters(page)
            .mapCatching { mapper.mapToPage(it) }
            .onSuccess { save(it.items) }
    }

    suspend fun getCharacter(id: Int): Result<Character> {
        return getCharacterFromCache(id)?.let { Result.success(it) }
            ?: getCharacterFromApi(id).onSuccess { save(it) }
    }

    private fun getCharacterFromCache(characterId: Int): Character? {
        return dao.getCharacter(characterId)?.let { mapper.mapToModel(it) }
    }

    private suspend fun getCharacterFromApi(characterId: Int): Result<Character> {
        return dataSource.getCharacter(characterId).mapCatching { mapper.mapToModel(it) }
    }

    private suspend fun save(character: Character) {
        dao.insertCharacter(mapper.mapToEntity(character))
    }

    private suspend fun save(characters: List<Character>) {
        dao.insertCharacters(characters.map { mapper.mapToEntity(it) })
    }
}
