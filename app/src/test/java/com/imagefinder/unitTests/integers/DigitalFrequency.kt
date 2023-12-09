package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test


class DigitalFrequency {

    // Generate digit frequency map for each integer and compare them
    private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
        val i1Str = i1.toString()
        val i2Str = i2.toString()

        if (i1Str.length != i2Str.length) {
            return false
        }

        val frequencyCounter1 = i1Str.groupingBy { it }.eachCount()
        val frequencyCounter2 = i2Str.groupingBy { it }.eachCount()
        return frequencyCounter1 == frequencyCounter2
    }

    @Test
    fun `'789' and '897' have the same digit frequency`() {
        assertEquals(true, equalDigitFrequency(789, 897))
    }

    @Test
    fun `'123445' and '451243' have the same digit frequency`() {
        assertEquals(true, equalDigitFrequency(123445, 451243))
    }

    @Test
    fun `'447' and '477' have different digit frequency`() {
        assertEquals(false, equalDigitFrequency(447, 477))
    }

    @Test
    fun `'578' and '0' have different digit frequency`() {
        assertEquals(false, equalDigitFrequency(578, 0))
    }

    @Test
    fun `'0' and '0' have the same digit frequency`() {
        assertEquals(true, equalDigitFrequency(0, 0))
    }
}