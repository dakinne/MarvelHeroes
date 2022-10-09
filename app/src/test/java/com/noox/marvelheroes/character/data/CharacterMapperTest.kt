package com.noox.marvelheroes.character.data

import com.noox.marvelheroes.util.Constants
import org.junit.Test
import org.junit.Assert.assertEquals

class CharacterMapperTest {

    private val mapper = CharacterMapper()

    private val constants by lazy { Constants() }

    @Test
    fun `Should return a Character model when mapping a CharacterDataWrapper`() {
        val expected = constants.character
        val input = constants.characterDataWrapper

        val model = mapper.mapToModel(input)

        assertEquals(expected, model)
    }
}
