package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall
import javax.inject.Inject

class SerieDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getFirst20SeriesOfCharacter(characterId: Int): Result<SerieDataWrapper> = safeApiCall {
        apiService.getSeriesOfCharacter(characterId = characterId, limit = 20, offset = 0)
    }
}
