package com.noox.marvelheroes.comic.data.api

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall

class ComicDataSource(
    private val apiService: ApiService
) {

    suspend fun getFirst20ComicsOfCharacter(characterId: Int): Result<ComicDataWrapper> = safeApiCall {
        apiService.getComicsOfCharacter(characterId = characterId, limit = 20, offset = 0)
    }
}
