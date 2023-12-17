package com.imagefinder.unitTests

import com.imagefinder.Utils.getPrettyTimeStampFromString
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun `countUniqueValues empty list return 0`() {
        val date = DateTime(2020, 1, 1, 1, 1).toString()
        Assert.assertEquals("January 1, 2020", getPrettyTimeStampFromString(date))
    }
    @Test
    fun `null test`() {
        Assert.assertEquals("", getPrettyTimeStampFromString(null))
    }
    @Test
    fun `blank test`() {
        Assert.assertEquals("", getPrettyTimeStampFromString(""))
    }
}