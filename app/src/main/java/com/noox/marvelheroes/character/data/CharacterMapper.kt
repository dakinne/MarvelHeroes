package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.Page
import com.noox.marvelheroes.core.exception.BadDataException
import kotlin.math.roundToInt

class CharacterMapper {

    fun mapToPage(dto: CharacterDataWrapper): Page<Character> {
        val data = dto.data ?: throw BadDataException()
        // TODO: Test if limit, offset or/and total are nulls
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
            image = mapToModel(dto.thumbnail!!),
            comics = dto.comics?.available ?: 0,
            events = dto.events?.available ?: 0,
            series = dto.series?.available ?: 0,
            stories = dto.stories?.available ?: 0
        )
    }

    private fun mapToModel(dto: Image): String {
        return "${dto.path!!}.${dto.extension!!}".replace("http:", "https:")
    }
}
