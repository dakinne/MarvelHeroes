package com.noox.marvelheroes.event.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.util.Constants
import org.junit.Test
import org.junit.Assert.assertEquals

class EventMapperTest {

    private val mapper = EventMapper(ImageMapper())

    private val constants by lazy { Constants() }

    @Test
    fun `Should return a List of Event models when mapping a EventDataWrapper`() {
        val expected = listOf(constants.event)
        val input = constants.eventDataWrapper

        val model = mapper.mapToList(input)

        assertEquals(expected, model)
    }
}
