package com.noox.marvelheroes.comic.data

import com.noox.marvelheroes.comic.domain.model.Comic

class ComicRepository(
    private val dataSource: ComicDataSource,
    private val mapper: ComicMapper
) {

    suspend fun getFirst20ComicsOfCharacter(characterId: Int): Result<List<Comic>> {
        return dataSource.getFirst20ComicsOfCharacter(characterId).mapCatching { mapper.mapToList(it) }
    }
}
