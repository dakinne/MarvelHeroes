package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.event.data.api.EventDataSource
import com.noox.marvelheroes.event.data.cache.EventDao
import com.noox.marvelheroes.event.domain.model.Event
import com.noox.marvelheroes.util.Constants
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class EventRepositoryTest {

    private val dataSource = mockk<EventDataSource>()
    private val mapper = mockk<EventMapper>()
    private val dao = mockk<EventDao>()
    private val repository = EventRepository(dataSource, mapper, dao)

    private val constants by lazy { Constants() }
    private val characterId = 1
    private val event = constants.event
    private val eventEntity = constants.eventEntity
    private val eventWrapper = constants.eventDataWrapper

    @Test
    fun `Should return Success when request events is success` () = runTest {

        coEvery { dataSource.getFirst20EventsOfCharacter(characterId) } answers { Result.success(eventWrapper) }
        coEvery { dao.getEventsOfCharacter(any()) } returns emptyList()
        coEvery { dao.insertEvents(any()) } just runs
        every { mapper.mapToList(eventWrapper) } returns listOf(event)
        every { mapper.mapToEntity(characterId, event) } returns eventEntity

        val expected = Result.success(listOf(event))
        val result = repository.getFirst20EventsOfCharacter(characterId)

        assertTrue(result.isSuccess)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when map fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20EventsOfCharacter(characterId) } answers { Result.success(eventWrapper) }
        coEvery { dao.getEventsOfCharacter(any()) } returns emptyList()
        every { mapper.mapToList(eventWrapper) } throws exception

        val expected = Result.failure<Event>(exception)
        val result = repository.getFirst20EventsOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }

    @Test
    fun `Should return Failure when request events fail` () = runTest {

        val exception = Exception()
        coEvery { dataSource.getFirst20EventsOfCharacter(characterId) } answers { Result.failure(exception) }
        coEvery { dao.getEventsOfCharacter(any()) } returns emptyList()

        val expected = Result.failure<Event>(exception)
        val result = repository.getFirst20EventsOfCharacter(characterId)

        assertTrue(result.isFailure)
        assertEquals(expected, result)
    }
}
