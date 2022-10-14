package com.noox.marvelheroes.serie.data.api

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall

class SerieDataSource(
    private val apiService: ApiService
) {

    suspend fun getFirst20SeriesOfCharacter(characterId: Int): Result<SerieDataWrapper> = safeApiCall {
        apiService.getSeriesOfCharacter(characterId = characterId, limit = 20, offset = 0)
    }
}
