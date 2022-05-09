package com.example.anibox.data.repository

import com.example.anibox.data.local.AnimeDataBase
import com.example.anibox.domain.model.Anime
import com.example.anibox.domain.repository.LocalDataSource

class LocalDataSourceImpl(animeDataBase: AnimeDataBase): LocalDataSource {

    private val animeDao = animeDataBase.animeDao()

    override suspend fun getSelectedAnime(animeId: Int): Anime {
        return animeDao.getSelectedAnime(animeId = animeId)
    }
}