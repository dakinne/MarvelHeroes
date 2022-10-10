package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.core.data.ImageDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataWrapper(
    @Json(name = "code") val code: Int? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "data") val data: CharacterDataContainer? = null
)

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    @Json(name = "results") val results: List<CharacterDTO> = emptyList(),
    @Json(name = "offset") val offset: Int? = null,
    @Json(name = "limit") val limit: Int? = null,
    @Json(name = "total") val total: Int? = null
)

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "thumbnail") val thumbnail: ImageDTO? = null,
    @Json(name = "comics") val comics: ComicList? = null,
    @Json(name = "events") val events: EventList? = null,
    @Json(name = "series") val series: SeriesList? = null
) {
    fun hasValidData() = id != null && name != null && thumbnail != null && thumbnail.hasValidData()
}

@JsonClass(generateAdapter = true)
data class ComicList(
    @Json(name = "available") val available: Int? = null
)

@JsonClass(generateAdapter = true)
data class EventList(
    @Json(name = "available") val available: Int? = null
)

@JsonClass(generateAdapter = true)
data class SeriesList(
    @Json(name = "available") val available: Int? = null
)
