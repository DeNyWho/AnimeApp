package com.example.animeapp.data.remote.api

import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.anime.dto.AnimeTopResponse
import com.example.animeapp.data.remote.models.anime.dto.PopularAnimeResponse

interface AnimeService {

    suspend fun getPopularAnime(): Resource<PopularAnimeResponse>

    suspend fun getTopAnime(page: Int): Resource<AnimeTopResponse>

}