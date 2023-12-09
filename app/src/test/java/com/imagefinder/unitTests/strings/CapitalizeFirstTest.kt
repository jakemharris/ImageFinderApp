package com.imagefinder.unitTests.strings

import org.junit.Assert.assertEquals
import org.junit.Test


class CapitalizeFirstTest {
    private fun capitalizeFirst(list: List<String>): List<String> {
        return list.map { it.replaceFirstChar { string -> string.uppercaseChar() } }
    }

    @Test
    fun `capitalize list with one string`() {
        assertEquals(listOf("Igor"), capitalizeFirst(listOf("igor")))
    }

    @Test
    fun `capitalize list with two strings`() {
        assertEquals(listOf("Igor", "Wojda"), capitalizeFirst(listOf("igor", "wojda")))
    }

    @Test
    fun `capitalize list with empty string`() {
        assertEquals(listOf(""), capitalizeFirst(listOf("")))
    }

    @Test
    fun `capitalize list with sentence`() {
        assertEquals(
            listOf(
                "What a",
                "Beautiful",
                "Morning",
            ), capitalizeFirst(listOf("what a", "beautiful", "morning"))
        )
    }

    // Recursive solution
    private object Solution2 {
        private fun capitalizeFirst(list: List<String>): List<String> {
            return if (list.isEmpty()) {
                emptyList()
            } else {
                listOf(list.first().replaceFirstChar { string -> string.uppercaseChar() }) + capitalizeFirst(list.drop(1))
            }
        }
    }

    // Recursive solution
    private object Solution3 {
        private fun capitalizeFirst(list: List<String>): List<String> {
            if (list.size == 1) {
                return list.map { it.replaceFirstChar { string -> string.uppercaseChar() } }
            }

            return list.take(1)
                .map { it.replaceFirstChar { string -> string.uppercaseChar() } } + capitalizeFirst(list.drop(1))
        }
    }
}