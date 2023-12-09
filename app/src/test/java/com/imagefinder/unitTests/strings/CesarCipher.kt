package com.imagefinder.unitTests.strings

import org.junit.Assert.assertEquals
import org.junit.Test


class CesarCipher {
    private fun encodeCaesarCipher(str: String, shift: Int): String {
        val aCode = 'a'.code
        val zCode = 'z'.code
        val realShift = shift % (zCode - aCode + 1)

        return str.map {
            var code = it.code
            code += realShift

            if (code > zCode) {
                code = aCode + (code % zCode) - 1
            }

            code.toChar()
        }.joinToString(separator = "")


    }

    @Test
    fun `'abc' with shift 1 return 'bcd'`() {
        assertEquals("bcd", encodeCaesarCipher("abc", 1))
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 1 returns 'bcdefghijklmnopqrstuvwxyza'`() {
        assertEquals(
            "bcdefghijklmnopqrstuvwxyza",
            encodeCaesarCipher("abcdefghijklmnopqrstuvwxyz", 1)
        )
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 7 returns 'hijklmnopqrstuvwxyzabcdefg'`() {
        assertEquals(
            "hijklmnopqrstuvwxyzabcdefg", encodeCaesarCipher(
                "abcdefghijklmnopqrstuvwxyz",
                7,
            )
        )
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 50 returns 'yzabcdefghijklmnopqrstuvwx'`() {
        assertEquals(
            "yzabcdefghijklmnopqrstuvwx", encodeCaesarCipher(
                "abcdefghijklmnopqrstuvwxyz",
                50,
            )
        )
    }

    private object Solution2 {
        private fun encodeCaesarCipher(str: String, shift: Int): String {
            val alphabet = "abcdefghijklmnopqrstuvwxyz"

            var encoded = ""

            str.forEach {
                val indexInAlphabet = alphabet.indexOf(it)
                val newIndex = (indexInAlphabet + shift) % alphabet.length
                encoded += alphabet[newIndex]
            }

            return encoded
        }
    }
}