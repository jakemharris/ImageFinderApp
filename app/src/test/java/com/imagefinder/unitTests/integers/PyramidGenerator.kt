package com.imagefinder.unitTests.integers

import org.junit.Assert.assertEquals
import org.junit.Test


class PyramidGenerator {

    private fun generatePyramid(n: Int): List<String> {
        val list = mutableListOf<String>()
        val numColumns = (n * 2) - 1

        (0 until n).forEach { row ->
            val numHashes = (row * 2) + 1
            val numSpaces = numColumns - numHashes
            var sideString = ""
            repeat(numSpaces / 2) { sideString += " " }
            var hashString = ""
            repeat(numHashes) { hashString += "#" }
            list.add("$sideString$hashString$sideString")
        }

        return list
    }


    @Test
    fun `pyramid n = 2`() {
        assertEquals(2, generatePyramid(2).size)
        assertEquals(" # ", generatePyramid(2)[0])
        assertEquals("###", generatePyramid(2)[1])
    }

    @Test
    fun `pyramid n = 3`() {
        assertEquals(3, generatePyramid(3).size)
        assertEquals("  #  ", generatePyramid(3)[0])
        assertEquals(" ### ", generatePyramid(3)[1])
        assertEquals("#####", generatePyramid(3)[2])
    }

    @Test
    fun `pyramid n = 4`() {
        assertEquals(4, generatePyramid(4).size)
        assertEquals("   #   ", generatePyramid(4)[0])
        assertEquals("  ###  ", generatePyramid(4)[1])
        assertEquals(" ##### ", generatePyramid(4)[2])
        assertEquals("#######", generatePyramid(4)[3])
    }

    // iterative solution - calculate mid point
    private object Solution2 {
        private fun generatePyramid(n: Int): List<String> {
            val list = mutableListOf<String>()
            val midpoint = ((2 * n) - 1) / 2
            val numColumns = (n * 2) - 1

            (0 until n).forEach { row ->
                var rowStr = ""
                (0 until numColumns).forEach { column ->
                    rowStr += if (midpoint - row <= column && midpoint + row >= column) {
                        "#"
                    } else {
                        " "
                    }
                }
                list.add(rowStr)
            }

            return list
        }
    }

    // simplified iterative solution
    private object Solution3 {
        private fun generatePyramid(n: Int): MutableList<String> {
            val list = mutableListOf<String>()
            val maxRowLen = n * 2 - 1

            for (i in 1..n) {
                val rowLen = i * 2 - 1

                val sideString = " ".repeat((maxRowLen - rowLen) / 2)
                val hashString = "#".repeat(rowLen)

                list.add("$sideString$hashString$sideString")
            }
            return list
        }
    }
}