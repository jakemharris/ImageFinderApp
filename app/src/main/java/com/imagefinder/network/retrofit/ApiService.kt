package com.imagefinder.network.retrofit

import com.imagefinder.Utils
import com.imagefinder.dto.ImageModel
import com.imagefinder.dto.SearchPhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: ${Utils.PEXEL_API_KEY}", "Content-Type: application/json")
    @GET("search")
    suspend fun getImagesRequest(
        @Query("query") searchString: String
    ): Response<SearchPhotoResponse>

    @Headers("Authorization: ${Utils.PEXEL_API_KEY}", "Content-Type: application/json")
    @GET("photos/{photoId}")
    suspend fun getPhotoRequest(
        @Path("photoId") photoId: String
    ): Response<ImageModel>
}
