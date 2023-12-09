package com.imagefinder.dto

data class ImageModel(
    val alt: String,
    val id: String,
    val url: String,
    val photographer: String,
    val src: SrcImageData,
)

data class SrcImageData(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val landscape: String,
    val tiny: String,
    val portrait: String,
)

data class ImageDetailModel(
    val title: String,
    val id: String,
    val url: String,
    val description: String,
    val dateTaken: String,
    val datePosted: String,
)