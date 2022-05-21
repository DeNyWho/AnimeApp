package com.example.anibox.data.remote.models.manga.dto

import MangaDetailsDtoV4
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaDetailsResponse(
    @SerialName("data")
    val `data`: MangaDetailsDtoV4 = MangaDetailsDtoV4()
)