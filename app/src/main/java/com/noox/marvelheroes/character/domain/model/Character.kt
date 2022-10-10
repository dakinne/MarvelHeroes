package com.noox.marvelheroes.character.domain.model

import android.os.Parcelable
import com.noox.marvelheroes.comic.domain.model.Comic
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.event.domain.model.Event
import com.noox.marvelheroes.serie.domain.model.Serie
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val image: Image,
    val thumbnail: Image,
    val totalComics: Int,
    val totalEvents: Int,
    val totalSeries: Int,
    val comics: List<Comic> = emptyList(),
    val series: List<Serie> = emptyList(),
    val events: List<Event> = emptyList()
): Parcelable {

    @IgnoredOnParcel val hasComics = totalComics > 0
    @IgnoredOnParcel val hasSeries = totalSeries > 0
    @IgnoredOnParcel val hasEvents = totalEvents > 0
}
