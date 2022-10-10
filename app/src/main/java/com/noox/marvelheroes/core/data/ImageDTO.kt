package com.noox.marvelheroes.core.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDTO(
    @Json(name = "path") val path: String? = null,
    @Json(name = "extension") val extension: String? = null
) {
    fun hasValidData() = path != null && extension != null
}
