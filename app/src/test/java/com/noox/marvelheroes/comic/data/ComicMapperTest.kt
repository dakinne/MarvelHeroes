package com.noox.marvelheroes.comic.data

import com.noox.marvelheroes.core.data.ImageMapper
import com.noox.marvelheroes.util.Constants
import org.junit.Test
import org.junit.Assert.assertEquals

class ComicMapperTest {

    private val mapper = ComicMapper(ImageMapper())

    private val constants by lazy { Constants() }

    @Test
    fun `Should return a List of Comic models when mapping a ComicDataWrapper`() {
        val expected = listOf(constants.comic)
        val input = constants.comicDataWrapper

        val model = mapper.mapToList(input)

        assertEquals(expected, model)
    }
}
