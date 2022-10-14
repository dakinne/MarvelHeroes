package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.core.exception.BadDataException
import com.noox.marvelheroes.event.data.api.EventDTO
import com.noox.marvelheroes.event.data.api.EventDataWrapper
import com.noox.marvelheroes.event.data.cache.EventEntity
import com.noox.marvelheroes.event.domain.model.Event

private const val IMAGE_VARIANT_NAME = "portrait_fantastic"

class EventMapper(
    private val imageMapper: ImageMapper
) {

    fun mapToList(dto: EventDataWrapper): List<Event> {
        val data = dto.data ?: throw BadDataException()
        return data.results.filter { it.hasValidData() }.map { mapToModel(it) }
    }

    private fun mapToModel(dto: EventDTO) = Event(
        image = imageMapper.mapToModel(dto.thumbnail!!, IMAGE_VARIANT_NAME)
    )

    fun mapToModel(entity: EventEntity) = Event(
        image = Image(entity.imageUrl)
    )

    fun mapToEntity(characterId: Int, event: Event) = EventEntity(
        characterId = characterId,
        imageUrl = event.image.url
    )
}
