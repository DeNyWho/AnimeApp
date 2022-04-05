package com.example.animeapp.data.repository

import com.example.animeapp.data.local.AnimeDataBase
import com.example.animeapp.domain.model.Anime
import com.example.animeapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(animeDataBase: AnimeDataBase): LocalDataSource {

    private val animeDao = animeDataBase.animeDao()

    override suspend fun getSelectedAnime(animeId: Int): Anime {
        return animeDao.getSelectedAnime(animeId = animeId)
    }
}