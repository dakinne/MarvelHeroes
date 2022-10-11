package com.noox.marvelheroes.character.domain.usecase

import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.model.Character
import com.noox.marvelheroes.comic.data.ComicRepository
import com.noox.marvelheroes.event.data.EventRepository
import com.noox.marvelheroes.serie.data.SerieRepository
import com.noox.marvelheroes.util.Constants
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharacterTest {

    private val characterRepository = mockk<CharacterRepository>()
    private val comicRepository = mockk<ComicRepository>()
    private val serieRepository = mockk<SerieRepository>()
    private val eventRepository = mockk<EventRepository>()

    private val getCharacter = GetCharacter(
        characterRepository = characterRepository,
        comicRepository = comicRepository,
        serieRepository = serieRepository,
        eventRepository = eventRepository
    )

    private val constants by lazy { Constants() }
    private val character = constants.character
    private val comics = listOf(constants.comic)
    private val series = listOf(constants.serie)
    private val events = listOf(constants.event)

    @Test
    fun `Should return Failure when get character fail` () = runTest {

        val exception = Exception()
        coEvery { characterRepository.getCharacter(character.id) } answers { Result.failure(exception) }

        val expected = Result.failure<Character>(exception)
        val result = getCharacter(character.id)

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `Should call get comics when character has comics` () = runTest {

        val characterWithComics = character.copy(totalComics = 10)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithComics) }

        getCharacter(character.id)

        coVerify { comicRepository.getFirst20ComicsOfCharacter(character.id) }
    }

    @Test
    fun `Should not call get comics when character has not comics` () = runTest {

        val character = character.copy(totalComics = 0)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(character) }

        getCharacter(character.id)

        coVerify(exactly = 0) { comicRepository.getFirst20ComicsOfCharacter(character.id) }
    }

    @Test
    fun `Should return Failure when get comics fail` () = runTest {

        val characterWithComics = character.copy(totalComics = 10)
        val exception = Exception()

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithComics) }
        coEvery { comicRepository.getFirst20ComicsOfCharacter(character.id) } answers { Result.failure(exception) }

        val result = getCharacter(character.id)

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `Should call get series when character has series` () = runTest {

        val characterWithSeries = character.copy(totalSeries = 10)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithSeries) }

        getCharacter(character.id)

        coVerify { serieRepository.getFirst20SeriesOfCharacter(character.id) }
    }

    @Test
    fun `Should not call get series when character has not series` () = runTest {

        val character = character.copy(totalSeries = 0)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(character) }

        getCharacter(character.id)

        coVerify(exactly = 0) { serieRepository.getFirst20SeriesOfCharacter(character.id) }
    }

    @Test
    fun `Should return Failure when get series fail` () = runTest {

        val characterWithSeries = character.copy(totalSeries = 10)
        val exception = Exception()

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithSeries) }
        coEvery { serieRepository.getFirst20SeriesOfCharacter(character.id) } answers { Result.failure(exception) }

        val result = getCharacter(character.id)

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `Should call get events when character has events` () = runTest {

        val characterWithEvents = character.copy(totalEvents = 10)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithEvents) }

        getCharacter(character.id)

        coVerify { eventRepository.getFirst20EventsOfCharacter(character.id) }
    }

    @Test
    fun `Should not call get events when character has not events` () = runTest {

        val character = character.copy(totalEvents = 0)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(character) }

        getCharacter(character.id)

        coVerify(exactly = 0) { eventRepository.getFirst20EventsOfCharacter(character.id) }
    }

    @Test
    fun `Should return Failure when get events fail` () = runTest {

        val characterWithEvents = character.copy(totalEvents = 10)
        val exception = Exception()

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(characterWithEvents) }
        coEvery { eventRepository.getFirst20EventsOfCharacter(character.id) } answers { Result.failure(exception) }

        val result = getCharacter(character.id)

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `Should return Success when get character, comics, series and events success` () = runTest {

        val character = character.copy(totalEvents = 1, totalSeries = 1, totalComics = 1)

        coEvery { characterRepository.getCharacter(character.id) } answers { Result.success(character) }
        coEvery { comicRepository.getFirst20ComicsOfCharacter(character.id) } answers { Result.success(comics) }
        coEvery { serieRepository.getFirst20SeriesOfCharacter(character.id) } answers { Result.success(series) }
        coEvery { eventRepository.getFirst20EventsOfCharacter(character.id) } answers { Result.success(events) }

        val result = getCharacter(character.id)

        Assert.assertTrue(result.isSuccess)

        val resultCharacter = result.getOrNull()
        Assert.assertNotNull(resultCharacter)

        val expected = character.copy(comics = comics, series = series, events = events)
        Assert.assertEquals(expected,resultCharacter)
    }
}
