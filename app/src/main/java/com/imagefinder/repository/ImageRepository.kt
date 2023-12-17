package com.imagefinder.repository

import android.util.Log
import com.imagefinder.nontest.dto.ImageModel
import com.imagefinder.nontest.network.NetworkResult
import com.imagefinder.nontest.network.retrofit.NetworkService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ImageRepository @Inject constructor(
    private val networkService: NetworkService,
) {

    var selectedImageId: String? = null
    suspend fun getImages(
        searchString: String,
    ): NetworkResult<List<ImageModel>> {

        return try {
            val response = networkService.getImagesRequest(searchString)
            val photos = response.body()?.photos
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
