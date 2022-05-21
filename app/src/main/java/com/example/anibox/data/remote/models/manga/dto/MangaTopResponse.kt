package com.example.anibox.data.remote.models.manga.dto

import MangaTopDtoV4
import com.example.anibox.data.remote.models.anime.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaTopResponse(
    @SerialName("data")
    val `data`: List<MangaTopDtoV4> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)
