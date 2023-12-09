package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test


class GetOdd {

    private fun filterOdd(list: List<Int>): List<Int> {
        return list.filter { it % 2 == 1 }
    }

    @Test
    fun `empty list returns empty list`() {
        assertEquals(emptyList<Int>(), filterOdd(listOf()))
    }

    @Test
    fun `1, 2, 3 returns 1, 3`() {
        assertEquals(listOf(1, 3), filterOdd(listOf(1, 2, 3)))
    }

    @Test
    fun `2, 9, 2, 5, 4 returns 9, 5`() {
        assertEquals(listOf(9, 5), filterOdd(listOf(2, 9, 2, 5, 4)))
    }

    @Test
    fun `7, 4, 6, 8, 7, 9 returns 7, 7, 9`() {
        assertEquals(listOf(7, 7, 9), filterOdd(listOf(7, 4, 6, 8, 7, 9)))
    }

    // Recursive solution
    private object Solution2 {
        private fun filterOdd(list: List<Int>): List<Int> {
            if (list.isEmpty()) {
                return list
            }

            return if (list.first() % 2 == 1) {
                mutableListOf(list.first()) + filterOdd(list.drop(1))
            } else {
                filterOdd(list.drop(1))
            }
        }
    }
}
