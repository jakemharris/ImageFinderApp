package com.imagefinder.unitTests.strings

import org.junit.Assert.assertEquals
import org.junit.Test


class CountUniqueValuesTest {
    private fun countUniqueValues(list: List<Int>): Int {
        val settt = mutableSetOf<Int>()
        return list.toSet().size
    }

    @Test
    fun `countUniqueValues empty list return 0`() {
        assertEquals(0, countUniqueValues(listOf()))
    }

    @Test
    fun `countUniqueValues 4 return 1`() {
        assertEquals(1, countUniqueValues(listOf(4)))
    }

    @Test
    fun `countUniqueValues 3, 3, 3, 3, 5 return 2`() {
        assertEquals(2, countUniqueValues(listOf(3, 3, 3, 3, 5)))
    }

    @Test
    fun `countUniqueValues 5, 5, 6, 7, 7 returns 3`() {
        assertEquals(3, countUniqueValues(listOf(5, 5, 6, 7, 7)))
    }

    @Test
    fun `countUniqueValues 1, 5, 9, 9 returns 3`() {
        assertEquals(3, countUniqueValues(listOf(1, 5, 9, 9)))
    }

    @Test
    fun `countUniqueValues 1, 5, 5, 5, 9 returns 3`() {
        assertEquals(3, countUniqueValues(listOf(1, 5, 5, 5, 9)))
    }

    @Test
    fun `countUniqueValues 4, 4, 5, 7, 10, 10 returns 4`() {
        assertEquals(4, countUniqueValues(listOf(4, 4, 5, 7, 10, 10)))
    }

    @Test
    fun `countUniqueValues 2, 2, 3, 6, 7, 9, 9, 12, 13, 13 returns 7`() {
        assertEquals(7, countUniqueValues(listOf(2, 2, 3, 6, 7, 9, 9, 12, 13, 13)))
    }

    @Test
    fun `countUniqueValues 1, 2, 3, 4, 5 return 5`() {
        assertEquals(5, countUniqueValues(listOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `countUniqueValues 2, 3, 4, 7 return 4`() {
        assertEquals(4, countUniqueValues(listOf(2, 3, 4, 7)))
    }

    private object Solution3 {
        private fun countUniqueValues(list: List<Int>): Int {
            return list.distinct().size
        }
    }

    private object Solution4 {
        private fun countUniqueValues(list: List<Int>): Int {
            return list.groupBy { it }.size
        }
    }

    private object Solution1 {
        private fun countUniqueValues(list: List<Int>): Int {
            val map = mutableMapOf<Int, Int>()

            list.forEach {
                var value = map.getOrDefault(it, 0)
                value++
                map[it] = value
            }

            return map.count()
        }
    }
}
