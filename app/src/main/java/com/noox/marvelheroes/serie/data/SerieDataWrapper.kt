package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.core.data.ImageDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SerieDataWrapper(
    @Json(name = "code") val code: Int? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "data") val data: SerieDataContainer? = null
)

@JsonClass(generateAdapter = true)
data class SerieDataContainer(
    @Json(name = "results") val results: List<SerieDTO> = emptyList()
)

@JsonClass(generateAdapter = true)
data class SerieDTO(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "thumbnail") val thumbnail: ImageDTO? = null
) {
    fun hasValidData() = thumbnail != null && thumbnail.hasValidData()
}
