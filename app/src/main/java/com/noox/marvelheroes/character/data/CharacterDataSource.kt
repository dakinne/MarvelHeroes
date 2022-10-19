package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall
import com.noox.marvelheroes.core.data.PAGE_LIMIT
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCharacters(page: Int): Result<CharacterDataWrapper> = safeApiCall {
        apiService.getCharacters(limit = PAGE_LIMIT, offset = page * PAGE_LIMIT)
    }

    suspend fun getCharacter(id: Int): Result<CharacterDataWrapper> = safeApiCall {
        apiService.getCharacter(id)
    }
}
