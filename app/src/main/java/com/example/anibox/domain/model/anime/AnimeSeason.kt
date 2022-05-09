package com.example.anibox.domain.model.anime

data class AnimeSeason(
    val malId: Int,
    val rank: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val type: String,
    val episodesCount: Int?,
    val members: Int,
    val score: Double,
)
