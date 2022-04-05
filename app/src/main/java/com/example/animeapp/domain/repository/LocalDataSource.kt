package com.example.animeapp.domain.repository

import com.example.animeapp.domain.model.Anime

interface LocalDataSource {
    suspend fun getSelectedAnime(animeId: Int): Anime
}