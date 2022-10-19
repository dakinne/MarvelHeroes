package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.core.exception.BadDataException
import com.noox.marvelheroes.event.domain.model.Event
import javax.inject.Inject

private const val IMAGE_VARIANT_NAME = "portrait_fantastic"

class EventMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun mapToList(dto: EventDataWrapper): List<Event> {
        val data = dto.data ?: throw BadDataException()
        return data.results.filter { it.hasValidData() }.map { mapToModel(it) }
    }

    private fun mapToModel(dto: EventDTO) = Event(
        image = imageMapper.mapToModel(dto.thumbnail!!, IMAGE_VARIANT_NAME)
    )
}
