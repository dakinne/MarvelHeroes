package com.noox.marvelheroes.util

import com.noox.marvelheroes.character.data.CharacterDTO
import com.noox.marvelheroes.character.data.CharacterDataContainer
import com.noox.marvelheroes.character.data.CharacterDataWrapper
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.comic.data.ComicDTO
import com.noox.marvelheroes.comic.data.ComicDataContainer
import com.noox.marvelheroes.comic.data.ComicDataWrapper
import com.noox.marvelheroes.comic.domain.model.Comic
import com.noox.marvelheroes.core.data.ImageDTO
import com.noox.marvelheroes.core.data.Page
import com.noox.marvelheroes.core.domain.model.Image
import com.noox.marvelheroes.event.data.EventDTO
import com.noox.marvelheroes.event.data.EventDataContainer
import com.noox.marvelheroes.event.data.EventDataWrapper
import com.noox.marvelheroes.event.domain.model.Event
import com.noox.marvelheroes.serie.data.SerieDTO
import com.noox.marvelheroes.serie.data.SerieDataContainer
import com.noox.marvelheroes.serie.data.SerieDataWrapper
import com.noox.marvelheroes.serie.domain.model.Serie

class Constants {

    val characterDataWrapper by lazy {
        CharacterDataWrapper(
            code = 200,
            status = "Ok",
            data = CharacterDataContainer(
                results = listOf(
                    CharacterDTO(
                        id = 100,
                        name = "Black Panther",
                        thumbnail = ImageDTO(
                            path = "https//images.com/100",
                            extension = "jpg"
                        )
                    )
                )
            )
        )
    }

    val character by lazy {
        Character(
            id = 100,
            name = "Black Panther",
            image = Image("https//images.com/100/standard_fantastic.jpg"),
            thumbnail = Image("https//images.com/100/standard_medium.jpg"),
            totalComics = 0,
            totalEvents = 0,
            totalSeries = 0
        )
    }

    val serieDataWrapper by lazy {
        SerieDataWrapper(
            code = 200,
            status = "Ok",
            data = SerieDataContainer(
                results = listOf(
                    SerieDTO(
                        id = 100,
                        name = "Black Panther",
                        thumbnail = ImageDTO(
                            path = "https//images.com/100",
                            extension = "jpg"
                        )
                    )
                )
            )
        )
    }

    val serie by lazy {
        Serie(
            image = Image("https//images.com/100/portrait_fantastic.jpg"),
        )
    }

    val eventDataWrapper by lazy {
        EventDataWrapper(
            code = 200,
            status = "Ok",
            data = EventDataContainer(
                results = listOf(
                    EventDTO(
                        id = 100,
                        name = "Black Panther",
                        thumbnail = ImageDTO(
                            path = "https//images.com/100",
                            extension = "jpg"
                        )
                    )
                )
            )
        )
    }

    val event by lazy {
        Event(
            image = Image("https//images.com/100/portrait_fantastic.jpg"),
        )
    }

    val comicDataWrapper by lazy {
        ComicDataWrapper(
            code = 200,
            status = "Ok",
            data = ComicDataContainer(
                results = listOf(
                    ComicDTO(
                        id = 100,
                        name = "Black Panther",
                        thumbnail = ImageDTO(
                            path = "https//images.com/100",
                            extension = "jpg"
                        )
                    )
                )
            )
        )
    }

    val comic by lazy {
        Comic(
            image = Image("https//images.com/100/portrait_fantastic.jpg"),
        )
    }

    val page0 by lazy {
        Page(
            items = listOf(character),
            currentPage = 0,
            totalPages = 100
        )
    }
}
