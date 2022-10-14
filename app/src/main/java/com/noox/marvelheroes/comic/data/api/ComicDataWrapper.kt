package com.noox.marvelheroes.comic.data.api

import com.noox.marvelheroes.core.data.ImageDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicDataWrapper(
    @Json(name = "code") val code: Int? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "data") val data: ComicDataContainer? = null
)

@JsonClass(generateAdapter = true)
data class ComicDataContainer(
    @Json(name = "results") val results: List<ComicDTO> = emptyList()
)

@JsonClass(generateAdapter = true)
data class ComicDTO(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "thumbnail") val thumbnail: ImageDTO? = null
) {
    fun hasValidData() = thumbnail != null && thumbnail.hasValidData()
}
