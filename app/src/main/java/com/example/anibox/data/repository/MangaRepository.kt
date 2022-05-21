package com.example.anibox.data.repository

import com.example.anibox.core.Endpoints
import com.example.anibox.core.SafeCall
import com.example.anibox.core.error.GeneralError
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.api.MangaService
import com.example.anibox.data.remote.models.manga.dto.MangaDetailsResponse
import com.example.anibox.data.remote.models.manga.dto.MangaTopResponse
import com.example.anibox.di.AndroidKtorClient
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaRepository @Inject constructor(
    @AndroidKtorClient private val client: HttpClient,
    private val safeCall: SafeCall
): MangaService {
    override suspend fun getTopManga(page: Int): Resource<MangaTopResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = Endpoints.MANGA_TOP
                parameter("page", page)
                parameter("filter", "bypopularity")
            }
        }

        return safeCall <MangaTopResponse, GeneralError> (client, request)
    }

    override suspend fun getMangaDetails(malId: Int): Resource<MangaDetailsResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.HOST_V4
                encodedPath = Endpoints.MANGA_DETAILS + "/$malId"
            }
        }

        return safeCall<MangaDetailsResponse, GeneralError>(client, request)
    }

}