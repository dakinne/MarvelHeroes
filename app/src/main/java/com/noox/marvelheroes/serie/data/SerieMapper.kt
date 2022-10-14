package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.core.exception.BadDataException
import com.noox.marvelheroes.serie.data.api.SerieDTO
import com.noox.marvelheroes.serie.data.api.SerieDataWrapper
import com.noox.marvelheroes.serie.data.cache.SerieEntity
import com.noox.marvelheroes.serie.domain.model.Serie

private const val IMAGE_VARIANT_NAME = "portrait_fantastic"

class SerieMapper(
    private val imageMapper: ImageMapper
) {

    fun mapToList(dto: SerieDataWrapper): List<Serie> {
        val data = dto.data ?: throw BadDataException()
        return data.results.filter { it.hasValidData() }.map { mapToModel(it) }
    }

    private fun mapToModel(dto: SerieDTO) = Serie(
        image = imageMapper.mapToModel(dto.thumbnail!!, IMAGE_VARIANT_NAME)
    )

    fun mapToModel(entity: SerieEntity) = Serie(
        image = Image(entity.imageUrl)
    )

    fun mapToEntity(characterId: Int, serie: Serie) = SerieEntity(
        characterId = characterId,
        imageUrl = serie.image.url
    )
}
