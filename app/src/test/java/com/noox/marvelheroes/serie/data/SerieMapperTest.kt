package com.noox.marvelheroes.serie.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.util.Constants
import org.junit.Test
import org.junit.Assert.assertEquals

class SerieMapperTest {

    private val mapper = SerieMapper(ImageMapper())

    private val constants by lazy { Constants() }

    @Test
    fun `Should return a List of Serie models when mapping a SerieDataWrapper`() {
        val expected = listOf(constants.serie)
        val input = constants.serieDataWrapper

        val model = mapper.mapToList(input)

        assertEquals(expected, model)
    }
}
