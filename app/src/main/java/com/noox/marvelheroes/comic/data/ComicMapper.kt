package com.noox.marvelheroes.comic.data

import com.noox.marvelheroes.comic.domain.model.Comic
import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.core.exception.BadDataException
import javax.inject.Inject

private const val IMAGE_VARIANT_NAME = "portrait_fantastic"

class ComicMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun mapToList(dto: ComicDataWrapper): List<Comic> {
        val data = dto.data ?: throw BadDataException()
        return data.results.filter { it.hasValidData() }.map { mapToModel(it) }
    }

    private fun mapToModel(dto: ComicDTO) = Comic(
        image = imageMapper.mapToModel(dto.thumbnail!!, IMAGE_VARIANT_NAME)
    )
}
