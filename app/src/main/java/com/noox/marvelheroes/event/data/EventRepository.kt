package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.event.domain.model.Event
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val dataSource: EventDataSource,
    private val mapper: EventMapper
) {

    suspend fun getFirst20EventsOfCharacter(characterId: Int): Result<List<Event>> {
        return dataSource.getFirst20EventsOfCharacter(characterId).mapCatching { mapper.mapToList(it) }
    }
}
