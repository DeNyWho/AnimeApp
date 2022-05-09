package com.example.anibox.data.repository

import com.example.anibox.core.Endpoints
import com.example.anibox.core.SafeCall
import com.example.anibox.core.error.GeneralError
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.api.AnimeService
import com.example.anibox.data.remote.models.anime.dto.AnimeSeasonResponseV4
import com.example.anibox.data.remote.models.anime.dto.AnimeTopResponse
import com.example.anibox.data.remote.models.anime.dto.PopularAnimeResponse
import com.example.anibox.di.AndroidKtorClient
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.data.remote.models.user.UserSignResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    @AndroidKtorClient private val client: HttpClient,
    private val safeCall: SafeCall
): AnimeService {
    override suspend fun getSeasonAnime(
        year: Int,
        season: String
    ): Resource<AnimeSeasonResponseV4> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = "/seasons/$year/$season"
            }
        }

        return safeCall<AnimeSeasonResponseV4, GeneralError>(client, request)
    }

    override suspend fun getPopularAnime(): Resource<PopularAnimeResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = Endpoints.ANIME_DETAILS
                parameter("page", 1)
                parameter("status","airing")
                parameter("order_by","score")
                parameter("sort", "desc")
            }
        }

        val res = safeCall<PopularAnimeResponse, GeneralError>(client, request)

        Timber.d(res.data?.toString())
        Timber.d(res.message.toString())
        return res
    }

    override suspend fun getTopAnime(page: Int): Resource<AnimeTopResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = Endpoints.ANIME_TOP
                parameter("page", page)
            }
        }

        return safeCall <AnimeTopResponse, GeneralError> (client, request)
    }

    override suspend fun getLogin(user: UserLoginDto): Resource<UserResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            url(Endpoints.USER_LOGIN)
            contentType(ContentType.Application.Json)
            body = user
        }

        return safeCall<UserResponse, GeneralError>(client, request)
    }

    @OptIn(InternalAPI::class)
    override suspend fun getSign(user: UserDto): Resource<UserSignResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            url(Endpoints.USER_SIGN)
            contentType(ContentType.Application.Json)
            body = user
        }

        return safeCall <UserSignResponse, GeneralError> (client, request)
    }

    override suspend fun getSqlInfo(email: String): Resource<UserDto> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url(Endpoints.USER_INFO)
            parameter("email", email)
        }

        return safeCall <UserDto, GeneralError> (client, request)
    }

}