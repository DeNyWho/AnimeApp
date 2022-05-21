package com.example.anibox.data.remote.api

import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.AnimeDetailsDtoV4
import com.example.anibox.data.remote.models.anime.dto.AnimeSeasonResponseV4
import com.example.anibox.data.remote.models.anime.dto.AnimeTopResponse
import com.example.anibox.data.remote.models.anime.dto.PopularAnimeResponse

interface AnimeService {

    suspend fun getSeasonAnime(year: Int, season: String): Resource<AnimeSeasonResponseV4>

    suspend fun getPopularAnime(): Resource<PopularAnimeResponse>

    suspend fun getTopAnime(page: Int): Resource<AnimeTopResponse>

    suspend fun getAnimeDetails(malId: Int): Resource<AnimeDetailsDtoV4>

}