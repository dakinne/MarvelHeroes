package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.core.api.ApiService
import com.noox.marvelheroes.core.api.safeApiCall

class EventDataSource(
    private val apiService: ApiService
) {

    suspend fun getFirst20EventsOfCharacter(characterId: Int): Result<EventDataWrapper> = safeApiCall {
        apiService.getEventsOfCharacter(characterId = characterId, limit = 20, offset = 0)
    }
}
