package com.imagefinder.unitTests.integers

import org.junit.Test

import org.junit.Assert.*

class AddUpToTest {

    private fun addUpTo(n: Int): Int {

        return (0..n).sum()
    }

    @Test
    fun `add up to 1`() {
        assertEquals(1, addUpTo(1))
    }

    @Test
    fun `add up to 3`() {
        assertEquals(6, addUpTo(3))
    }

    @Test
    fun `add up to 10`() {
        assertEquals(55, addUpTo(10))
    }
}
