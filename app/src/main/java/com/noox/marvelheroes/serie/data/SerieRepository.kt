package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.serie.domain.model.Serie

class SerieRepository(
    private val dataSource: SerieDataSource,
    private val mapper: SerieMapper
) {

    suspend fun getFirst20SeriesOfCharacter(characterId: Int): Result<List<Serie>> {
        return dataSource.getFirst20SeriesOfCharacter(characterId).mapCatching { mapper.mapToList(it) }
    }
}
