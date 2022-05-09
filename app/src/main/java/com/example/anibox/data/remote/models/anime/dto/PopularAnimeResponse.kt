package com.example.anibox.data.remote.models.anime.dto

import com.example.anibox.domain.model.anime.AnimeAiringPopular
import com.example.anibox.data.remote.models.anime.Pagination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularAnimeResponse(
    @SerialName("data")
    val `data`: List<AnimeGeneralDto> = listOf(),
    @SerialName("pagination")
    val pagination: Pagination = Pagination()
)

fun AnimeGeneralDto.toAnimeAiringPopular(): AnimeAiringPopular {
    return AnimeAiringPopular(
        malId, rank, title,
        url, images.jpg.imageUrl, type,
        episodes,
        members, score ?: 0.0
    )
}
