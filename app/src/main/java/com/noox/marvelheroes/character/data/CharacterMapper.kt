package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.exception.BadDataException

class CharacterMapper {

    fun mapToModel(dto: CharacterDataWrapper): List<Character> {
        return dto.data?.results?.filter { it.hasValidData() }?.map { mapToModel(it) }
            ?: throw BadDataException()
    }

    private fun mapToModel(dto: CharacterDTO): Character {
        return Character(
            id = dto.id!!,
            name = dto.name!!,
            image = mapToModel(dto.thumbnail!!)
        )
    }

    private fun mapToModel(dto: Image) = "${dto.path!!}.${dto.extension!!}"
}
