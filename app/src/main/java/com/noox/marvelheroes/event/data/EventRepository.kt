package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.event.data.api.EventDataSource
import com.noox.marvelheroes.event.data.cache.EventDao
import com.noox.marvelheroes.event.domain.model.Event

class EventRepository(
    private val dataSource: EventDataSource,
    private val mapper: EventMapper,
    private val dao: EventDao
) {

    suspend fun getFirst20EventsOfCharacter(characterId: Int): Result<List<Event>> {
        val events = getEventsFromCache(characterId)
        if (events.isNotEmpty()) {
            return Result.success(events)
        }

        return getEventsFromApi(characterId).onSuccess { save(characterId, it) }
    }

    private fun getEventsFromCache(characterId: Int): List<Event> {
        return dao.getEventsOfCharacter(characterId)
            .map { mapper.mapToModel(it) }
    }

    private suspend fun getEventsFromApi(characterId: Int): Result<List<Event>> {
        return dataSource.getFirst20EventsOfCharacter(characterId)
            .mapCatching { mapper.mapToList(it) }
    }

    private suspend fun save(characterId: Int, events: List<Event>) {
        dao.insertEvents(events.map { mapper.mapToEntity(characterId, it) })
    }
}
