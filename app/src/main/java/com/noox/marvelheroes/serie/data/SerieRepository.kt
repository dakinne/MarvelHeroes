package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.serie.data.api.SerieDataSource
import com.noox.marvelheroes.serie.data.cache.SerieDao
import com.noox.marvelheroes.serie.domain.model.Serie

class SerieRepository(
    private val dataSource: SerieDataSource,
    private val mapper: SerieMapper,
    private val dao: SerieDao
) {

    suspend fun getFirst20SeriesOfCharacter(characterId: Int): Result<List<Serie>> {
        val series = getSeriesFromCache(characterId)
        if (series.isNotEmpty()) {
            return Result.success(series)
        }

        return getSeriesFromApi(characterId).onSuccess { save(characterId, it) }
    }

    private fun getSeriesFromCache(characterId: Int): List<Serie> {
        return dao.getSeriesOfCharacter(characterId)
            .map { mapper.mapToModel(it) }
    }

    private suspend fun getSeriesFromApi(characterId: Int): Result<List<Serie>> {
        return dataSource.getFirst20SeriesOfCharacter(characterId)
            .mapCatching { mapper.mapToList(it) }
    }

    private suspend fun save(characterId: Int, series: List<Serie>) {
        dao.insertSeries(series.map { mapper.mapToEntity(characterId, it) })
    }
}
