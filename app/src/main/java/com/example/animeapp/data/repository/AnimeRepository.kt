package com.example.animeapp.data.repository

import android.util.Log
import com.example.animeapp.core.Endpoints
import com.example.animeapp.core.SafeCall
import com.example.animeapp.core.error.GeneralError
import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.api.AnimeService
import com.example.animeapp.data.remote.models.anime.dto.AnimeTopResponse
import com.example.animeapp.data.remote.models.anime.dto.PopularAnimeResponse
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.di.AndroidKtorClient
import com.example.animeapp.util.Result
import com.example.animeapp.util.SessionManager
import com.example.animeapp.util.isNetworkConnected
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
    private val safeCall: SafeCall,
    private val sessionManager: SessionManager
): AnimeService {
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
        Timber.d(res.message)
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

    override suspend fun getLogin(user: UserDto): Resource<UserResponse> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            url {
                protocol = URLProtocol.HTTP
                host = Endpoints.USER_HOST
                encodedPath = Endpoints.USER_LOGIN
                parameter("email", user.email)
                parameter("password", user.password)
            }
            Log.d("TAG", "$url")
        }

        return safeCall <UserResponse, GeneralError> (client, request)
    }

    @OptIn(InternalAPI::class)
    override suspend fun getSign(user: UserDto): Resource<UserResponse> {
//        return try {
//            if (!isNetworkConnected(sessionManager.context)) {
//                Result.Error<String>("No Internet Connection!")
//            }
//
//            val result = animeAPI.createAccount(user)
//
//            if (result.success) {
//                sessionManager.updateSession(result.message, user.userName ?: "", user.email)
//                Result.Success("User Created Successfully!")
//            } else {
//                Result.Error(result.message)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e.message ?: "Some Problem Occurred!")
//        }
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            url {
                "http://${Endpoints.USER_HOST}${Endpoints.USER_SIGN}"
            }
            Log.d("URL", url.toString())
            contentType(ContentType.Application.Json)
            setBody(user)
            Log.d("USERDTO","${user.email}, ${user.name}, ${user.password}")
        }

        return safeCall <UserResponse, GeneralError> (client, request)
    }

}