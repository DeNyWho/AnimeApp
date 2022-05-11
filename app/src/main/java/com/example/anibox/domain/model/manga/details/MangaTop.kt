package com.example.anibox.domain.model.manga.details

data class MangaTop(
    val malId: Int,
    val rank: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val type: String,
    val chaptersCount: Int?,
    val members: Int,
    val score: Double,
)
