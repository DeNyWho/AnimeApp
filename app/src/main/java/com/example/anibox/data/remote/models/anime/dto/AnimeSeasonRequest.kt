package com.example.anibox.data.remote.models.anime.dto

import com.example.anibox.domain.model.anime.AnimeSeason
import com.example.anibox.data.remote.models.anime.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeSeasonResponseV4(
    @SerialName("data")
    val `data`: List<AnimeGeneralDto> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)

fun AnimeGeneralDto.toAnimeSeason(): AnimeSeason {
    return AnimeSeason(
        malId, rank, title,
        url, images.jpg.imageUrl, type,
        episodes,
        members, score ?: 0.0
    )
}