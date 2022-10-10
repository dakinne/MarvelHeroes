package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.comic.data.ComicRepository
import com.noox.marvelheroes.serie.data.SerieRepository
import com.noox.marvelheroes.comic.domain.model.Comic
import com.noox.marvelheroes.event.data.EventRepository
import com.noox.marvelheroes.event.domain.model.Event
import com.noox.marvelheroes.serie.domain.model.Serie
import kotlinx.coroutines.*

class GetCharacter(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterRepository: CharacterRepository,
    private val comicRepository: ComicRepository,
    private val serieRepository: SerieRepository,
    private val eventRepository: EventRepository
) {
    suspend operator fun invoke(characterId: Int): Result<Character> = withContext(dispatcher) {

        characterRepository.getCharacter(characterId).mapCatching { character ->

            val comics = async(SupervisorJob()) { getComics(character) }
            val series = async(SupervisorJob()) { getSeries(character) }
            val events = async(SupervisorJob()) { getEvents(character) }

            character.copy(
                comics = comics.await(),
                series = series.await(),
                events = events.await()
            )
        }
    }

    private suspend fun getComics(character: Character): List<Comic> {
        if (!character.hasComics) {
            return emptyList()
        }

        return comicRepository.getFirst20ComicsOfCharacter(character.id).fold(
            onSuccess = { it },
            onFailure = { throw it }
        )
    }

    private suspend fun getSeries(character: Character): List<Serie> {
        if (!character.hasSeries) {
            return emptyList()
        }

        return serieRepository.getFirst20SeriesOfCharacter(character.id).fold(
            onSuccess = { it },
            onFailure = { throw it }
        )
    }

    private suspend fun getEvents(character: Character): List<Event> {
        if (!character.hasComics) {
            return emptyList()
        }

        return eventRepository.getFirst20EventsOfCharacter(character.id).fold(
            onSuccess = { it },
            onFailure = { throw it }
        )
    }

}
