package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test

class CountDownTest {

    private fun countDown(n: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 0..n) {
            list.add(i)
        }
        return list.reversed()
    }

    @Test
    fun `count down 0`() {
        System.out.println(countDown(0))
        assertEquals(countDown(0), listOf(0))
    }

    @Test
    fun `count down 1`() {
        System.out.println(countDown(1))
        assertEquals(countDown(1), listOf(1, 0))
    }

    @Test
    fun `count down 5`() {
        System.out.println(countDown(5))
        assertEquals(countDown(5), listOf(5, 4, 3, 2, 1, 0))
    }
}


//while (x > 0) {
//    x--
//}

//do {
//    val y = retrieveData()
//} while (y != null) // y is visible here!

//for (item in collection) print(item)

//for (item: Int in ints) {
//    // ...
//}

//for (i in 1..3) {
//    println(i)
//}

//for (i in 6 downTo 0 step 2) {
//    println(i)
//}


private object Solution1 {
    private fun countDown(n: Int): List<Int> {
        // Create a range and convert it to a list
        return (n downTo 0).toList()
    }
}

// Recursive solution
private object Solution2 {
    private fun countDown(n: Int): List<Int> {
        if (n == 0) {
            return listOf(0)
        }

        return mutableListOf(n).also { it.addAll(countDown(n - 1)) }
    }
}

// Recursive solution with helper function
private object Solution3 {
    private fun countDown(n: Int): List<Int> {
        // We want to keep return type unchanged while implementing recursive solution, so we will
        // use helper method defied inside countDown function.
        fun helper(n: Int): MutableList<Int> {
            if (n == 0) {
                return mutableListOf(0)
            }

            return mutableListOf(n).also { it.addAll(countDown(n - 1)) }
        }

        return helper(n).toList()
    }
}

// Kotlin idiomatic solution
private object Solution4 {
    private fun countDown(n: Int): List<Int> {
        return List(n + 1) { n - it }
    }
}