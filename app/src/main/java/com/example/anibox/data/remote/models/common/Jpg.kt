package com.example.animeapp.data.remote.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jpg(
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("small_image_url")
    val smallImageUrl: String = "",
    @SerialName("large_image_url")
    val largeImageUrl: String = ""
)
