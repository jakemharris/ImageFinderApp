package com.imagefinder.repository

import android.util.Log
import com.imagefinder.IO
import com.imagefinder.dto.ImageModel
import com.imagefinder.network.NetworkResult
import com.imagefinder.network.retrofit.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ImageRepository @Inject constructor(
    private val networkService: NetworkService,
    @IO private val ioDispatcher: CoroutineDispatcher,
) {


    suspend fun getImages(
        searchString: String,
    ): NetworkResult<List<ImageModel>> {

        return try {
            Log.w("jake", "get images() in repo  search string= " + searchString)
            val response = networkService.getImagesRequest(searchString)
            val photos = response.body()?.photos
            Log.w("jake", "response= " + response.toString())
            if (!response.isSuccessful) {
                NetworkResult.Error(Exception(response.errorBody().toString()))
            }
            if (photos.isNullOrEmpty()) {
                NetworkResult.Error(Exception("No images found"))
            } else {
                NetworkResult.Success(photos)
            }
        } catch (e: Exception) {
            NetworkResult.Error(Exception("Network failed"))
        }
    }


    suspend fun getPhotoByID(
        photoId: String,
    ): NetworkResult<ImageModel> {

        return try {
            Log.w("jake", "get images() in repo  search string= " + photoId)
            val response = networkService.getPhotoRequest(photoId = photoId)
            val image = response.body()
            if (!response.isSuccessful || image == null) {
                NetworkResult.Error(Exception(response.errorBody().toString()))
            } else {
                NetworkResult.Success(image)
            }
        } catch (e: Exception) {
            NetworkResult.Error(Exception("Network failed"))
        }
    }
}
