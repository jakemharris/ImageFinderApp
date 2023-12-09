package com.imagefinder.network.graphQL

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