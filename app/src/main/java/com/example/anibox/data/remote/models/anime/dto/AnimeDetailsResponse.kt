package com.example.anibox.data.remote.models.anime.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetailsResponse(
    @SerialName("data")
    val `data`: AnimeDetailsDtoV4 = AnimeDetailsDtoV4()
)