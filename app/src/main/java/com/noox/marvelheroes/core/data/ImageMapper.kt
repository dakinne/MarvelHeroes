package com.noox.marvelheroes.core.data

import com.noox.marvelheroes.core.domain.model.Image
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun mapToModel(dto: ImageDTO, variantName: String): Image {
        return Image("${dto.path!!}/$variantName.${dto.extension!!}")
    }
}
