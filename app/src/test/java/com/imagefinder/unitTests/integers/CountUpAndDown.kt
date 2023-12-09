package com.example.demomovies.integers

import org.junit.Test

import org.junit.Assert.*

class CountUpAndDown {

    private fun countUpAndDown(n: Int): List<Int> {
        val countUp = mutableListOf<Int>()
        for (i in 0..n) {
            countUp.add(i)
        }
        val reversed = mutableListOf<Int>()
        reversed.addAll(countUp.reversed())
        reversed.remove(n)
        countUp.addAll(reversed)
        return countUp


    }

    @Test
    fun `count up and down 0`() {
        assertEquals(listOf(0), countUpAndDown(0))
    }

    @Test
    fun `count up and down 1`() {
        assertEquals(listOf(0, 1, 0), countUpAndDown(1))
    }

    @Test
    fun `count up and down 2`() {
        assertEquals(listOf(0, 1, 2, 1, 0), countUpAndDown(2))
    }

    @Test
    fun `count up and down 3`() {
        assertEquals(listOf(0, 1, 2, 3, 2, 1, 0), countUpAndDown(3))
    }

    @Test
    fun `count up and down 4`() {
        assertEquals(listOf(0, 1, 2, 3, 4, 3, 2, 1, 0), countUpAndDown(4))
    }

    @Test
    fun `count up and down 9`() {
        assertEquals(
            listOf(
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                8,
                7,
                6,
                5,
                4,
                3,
                2,
                1,
                0,
            ), countUpAndDown(9)
        )
    }


    private object Solution1 {
        private fun countUpAndDown(n: Int): List<Int> {
            val size = (n * 2) + 1

            return List(size) {
                when {
                    it <= n -> it
                    else -> (n * 2) - it
                }
            }
        }
    }

    // Returns a new list combining one collection of numbers from 0 to n-1 and another with numbers from n to 0
    private object Solution2 {
        private fun countUpAndDown(n: Int): List<Int> {
            return (0 until n) + (n downTo 0)
        }
    }
}