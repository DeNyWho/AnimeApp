package com.example.animeapp.data.remote.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg = Jpg(),
    @SerialName("webp")
    val webp: Webp = Webp()
)
