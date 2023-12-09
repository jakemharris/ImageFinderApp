package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test

class FactorialTest {
    private fun factorial(n: Int): Int {
        var total = 1

        (1..n).forEach {
            total *= it
        }

        return total
    }

    @Test
    fun `factorial 0 should equal 1`() {
        assertEquals(1, factorial(0))
    }

    @Test
    fun `factorial 3 should equal 6`() {
        assertEquals(6, factorial(3))
    }

    @Test
    fun `factorial 5 should equal 120`() {
        assertEquals(120, factorial(5))
    }

    @Test
    fun `factorial 10 should equal 3628800`() {
        assertEquals(3628800, factorial(10))
    }
}
