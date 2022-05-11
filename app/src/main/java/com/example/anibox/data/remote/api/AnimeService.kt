package com.example.anibox.data.remote.api

import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.AnimeSeasonResponseV4
import com.example.anibox.data.remote.models.anime.dto.AnimeTopResponse
import com.example.anibox.data.remote.models.anime.dto.PopularAnimeResponse
import com.example.anibox.data.remote.models.manga.dto.MangaTopResponse
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.data.remote.models.user.UserSignResponse

interface AnimeService {

    // ANIME

    suspend fun getSeasonAnime(year: Int, season: String): Resource<AnimeSeasonResponseV4>

    suspend fun getPopularAnime(): Resource<PopularAnimeResponse>

    suspend fun getTopAnime(page: Int): Resource<AnimeTopResponse>


    // MANGA

    suspend fun getTopManga(page: Int): Resource<MangaTopResponse>




    // USER

    suspend fun getLogin(user: UserLoginDto): Resource<UserResponse>

    suspend fun getSign(user: UserDto): Resource<UserSignResponse>

    suspend fun getSqlInfo(email: String): Resource<UserDto>

}