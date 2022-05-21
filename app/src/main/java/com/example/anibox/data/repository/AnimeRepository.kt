package com.example.anibox.data.repository

import com.example.anibox.core.Endpoints
import com.example.anibox.core.SafeCall
import com.example.anibox.core.error.GeneralError
import com.example.anibox.core.exception.MyError
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.api.AnimeService
import com.example.anibox.data.remote.models.anime.dto.*
import com.example.anibox.di.AndroidKtorClient
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
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
                parameter("filter","bypopularity")
            }
        }

        return safeCall <AnimeTopResponse, GeneralError> (client, request)
    }

    override suspend fun getAnimeDetails(malId: Int): Resource<AnimeDetailsDtoV4> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = Endpoints.ANIME_DETAILS + "/$malId"
            }
        }

        val res = safeCall<AnimeDetailsResponse, GeneralError>(client, request)

        Timber.d("res repository ${res.data}")

        return if (res is Resource.Success && res.data != null) {
            Resource.Success(res.data.data)
        } else {
            Resource.Error(res.message ?: MyError.UNKNOWN_ERROR)
        }
    }
}