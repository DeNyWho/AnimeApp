package com.example.animeapp.data.remote.models.anime.dto

import com.example.animeapp.data.remote.models.anime.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTopResponse(
    @SerialName("data")
    val `data`: List<AnimeTopDtoV4> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)