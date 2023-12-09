package com.imagefinder.unitTests.strings

import org.junit.Assert.assertEquals
import org.junit.Test


class CapitalizeSentence {
    private fun capitalizeSentence(str: String): String {
        return str
            .split(" ")
            .joinToString(" ") { string ->
                string.replaceFirstChar { it.uppercase() }
            }
    }

    @Test
    fun `flower is capitalized to Flower`() {
        assertEquals("Flower", capitalizeSentence("flower"))
    }

    @Test
    fun `this is a house is capitalised to This Is A House`() {
        assertEquals("This Is A House", capitalizeSentence("this is a house"))
    }

    private object Solution2 {
        private fun capitalizeSentence(str: String): String {
            val words = mutableListOf<String>()

            str.split(" ").forEach {
                words.add(it[0].uppercase() + it.substring(1))
            }

            return words.joinToString(" ")
        }
    }
}