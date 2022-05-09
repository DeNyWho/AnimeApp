package com.example.anibox.domain.repository

import com.example.anibox.domain.model.Anime

interface LocalDataSource {
    suspend fun getSelectedAnime(animeId: Int): Anime
}