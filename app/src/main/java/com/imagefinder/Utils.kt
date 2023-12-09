package com.imagefinder

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object Utils {
    const val PEXEL_API_KEY = "WZevlYdlXiLXXap0i6GXyW2SveNjKi0oxzG083PA7imCkq9XsSdshS68"

    fun getPrettyTimeStampFromString(
        timestamp: String?,
    ): String {
        if (timestamp.isNullOrBlank()) {
            return ""
        }
        val dateIn = DateTime(timestamp)
        return dateIn.toString(DateTimeFormat.longDate())
    }

}
