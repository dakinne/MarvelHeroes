package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall

class CharacterDataSource(
    private val apiService: ApiService
) {

    suspend fun getCharacters(): Result<CharacterDataWrapper> = safeApiCall {
        apiService.getCharacters()
    }

    suspend fun getCharacter(id: Int): Result<CharacterDataWrapper> = safeApiCall {
        apiService.getCharacter(id)
    }
}
