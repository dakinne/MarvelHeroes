package com.noox.marvelheroes.comic.data

import com.noox.marvelheroes.comic.data.api.ComicDataSource
import com.noox.marvelheroes.comic.data.cache.ComicDao
import com.noox.marvelheroes.comic.domain.model.Comic

class ComicRepository(
    private val dataSource: ComicDataSource,
    private val mapper: ComicMapper,
    private val dao: ComicDao
) {

    suspend fun getFirst20ComicsOfCharacter(characterId: Int): Result<List<Comic>> {
        val comics = getComicsFromCache(characterId)
        if (comics.isNotEmpty()) {
            return Result.success(comics)
        }

        return getComicsFromApi(characterId).onSuccess { save(characterId, it) }
    }

    private fun getComicsFromCache(characterId: Int): List<Comic> {
        return dao.getComicsOfCharacter(characterId)
            .map { mapper.mapToModel(it) }
    }

    private suspend fun getComicsFromApi(characterId: Int): Result<List<Comic>> {
        return dataSource.getFirst20ComicsOfCharacter(characterId)
            .mapCatching { mapper.mapToList(it) }
    }

    private suspend fun save(characterId: Int, comics: List<Comic>) {
        dao.insertComics(comics.map { mapper.mapToEntity(characterId, it) })
    }
}
