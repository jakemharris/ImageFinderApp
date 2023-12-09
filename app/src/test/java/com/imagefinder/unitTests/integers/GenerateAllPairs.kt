package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test

class GenerateAllPairs {
    private fun getAllPairs(n: Int): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        (0..n).forEach { i ->
            (0..n).forEach { j ->
                result.add(i to j)
            }
        }

        return result
    }

    @Test
    fun `get all pairs 0`() {
        assertEquals(listOf(0 to 0), getAllPairs(0))
    }

    @Test
    fun `get all pairs 1`() {
        assertEquals(
            listOf(
                0 to 0,
                0 to 1,
                1 to 0,
                1 to 1,
            ), getAllPairs(1)
        )
    }

    @Test
    fun `get all pairs 2`() {
        assertEquals(
            listOf(
                0 to 0,
                0 to 1,
                0 to 2,
                1 to 0,
                1 to 1,
                1 to 2,
                2 to 0,
                2 to 1,
                2 to 2,
            ), getAllPairs(2)
        )
    }

    private object Solution2 {
        private fun getAllPairs(n: Int): List<Pair<Int, Int>> {
            return (0..n).map { i ->
                (0..n).map { j ->
                    i to j
                }
            }.flatten()
        }
    }
}
