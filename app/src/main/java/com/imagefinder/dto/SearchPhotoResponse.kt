package com.imagefinder.dto

data class SearchPhotoResponse(
    val page: Int,
    val per_page: Int,
    val photos: List<ImageModel>

)