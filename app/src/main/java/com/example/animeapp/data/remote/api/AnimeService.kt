package com.example.animeapp.data.remote.api

import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.anime.dto.AnimeTopResponse
import com.example.animeapp.data.remote.models.anime.dto.PopularAnimeResponse
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.util.Result

interface AnimeService {

    suspend fun getPopularAnime(): Resource<PopularAnimeResponse>

    suspend fun getTopAnime(page: Int): Resource<AnimeTopResponse>

    suspend fun getLogin(user: UserDto): Resource<UserResponse>

    suspend fun getSign(user: UserDto): Resource<UserResponse>

}