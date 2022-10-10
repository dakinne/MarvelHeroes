package com.noox.marvelheroes.util

import com.noox.marvelheroes.character.data.CharacterDTO
import com.noox.marvelheroes.character.data.CharacterDataContainer
import com.noox.marvelheroes.character.data.CharacterDataWrapper
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.core.data.ImageDTO
import com.noox.marvelheroes.core.domain.model.Image

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
            image = Image("https//images.com/100.jpg"),
            thumbnail = Image("https//images.com/100.jpg"),
            totalComics = 0,
            totalEvents = 0,
            totalSeries = 0
        )
    }
}
