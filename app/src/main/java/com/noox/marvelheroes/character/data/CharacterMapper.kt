package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.core.data.Page
import com.noox.marvelheroes.core.exception.BadDataException
import kotlin.math.roundToInt

private const val IMAGE_VARIANT_NAME = "standard_fantastic"
private const val THUMBNAIL_VARIANT_NAME = "standard_medium"

class CharacterMapper(
    private val imageMapper: ImageMapper
) {

    fun mapToPage(dto: CharacterDataWrapper): Page<Character> {
        val data = dto.data ?: throw BadDataException()
        val limit = data.limit ?: throw BadDataException()
        val offset = data.offset ?: throw BadDataException()
        val total = data.total ?: throw BadDataException()

        return Page(
            items = data.results.filter { it.hasValidData() }.map { mapToModel(it) },
            currentPage = offset / limit,
            totalPages = total.toDouble().div(limit).roundToInt()
        )
    }

    fun mapToModel(dto: CharacterDataWrapper): Character {
        val characterDTO = dto.data?.results?.first { it.hasValidData() }
            ?: throw BadDataException()

        return mapToModel(characterDTO)
    }

    private fun mapToModel(dto: CharacterDTO): Character {
        return Character(
            id = dto.id!!,
            name = dto.name!!,
            image = imageMapper.mapToModel(dto.thumbnail!!, IMAGE_VARIANT_NAME),
            thumbnail = imageMapper.mapToModel(dto.thumbnail, THUMBNAIL_VARIANT_NAME),
            totalComics = dto.comics?.available ?: 0,
            totalEvents = dto.events?.available ?: 0,
            totalSeries = dto.series?.available ?: 0
        )
    }
}
