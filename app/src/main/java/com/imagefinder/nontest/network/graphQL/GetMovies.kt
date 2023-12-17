package com.imagefinder.nontest.network.graphQL

object GetMovies {
    fun get(
        phoneOrEmail: String,
    ) =
        """query GetMovies {
        userSsoDetails(emailOrPhone:"$phoneOrEmail") {
            ssoEnabled
            redirectUrl
        }
    }"""
}