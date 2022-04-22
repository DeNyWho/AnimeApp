package com.example.animeapp.data.remote.models.anime.dto

import com.example.animeapp.data.remote.models.anime.Pagination
import com.example.animeapp.domain.model.anime.AnimeTop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTopResponse(
    @SerialName("data")
    val `data`: List<AnimeTopResponse> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)