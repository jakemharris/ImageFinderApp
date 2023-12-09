package com.imagefinder.network.retrofit

import com.imagefinder.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val apiService: ApiService,
    @IO private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getImagesRequest(
        searchString: String
    ) = withContext(ioDispatcher) {
        apiService.getImagesRequest(searchString)
    }

    suspend fun getPhotoRequest(
        photoId: String
    ) = withContext(ioDispatcher) {
        apiService.getPhotoRequest(photoId)
    }
}
