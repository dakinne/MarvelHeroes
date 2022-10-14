package com.noox.marvelheroes.core.data

import com.noox.marvelheroes.core.domain.model.Image

class ImageMapper {

    fun mapToModel(dto: ImageDTO, variantName: String): Image {
        return Image("${dto.path!!}/$variantName.${dto.extension!!}")
    }

    fun mapToModel(imageUrl: String) = Image(imageUrl)
}
