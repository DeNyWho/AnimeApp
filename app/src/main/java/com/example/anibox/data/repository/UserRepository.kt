package com.example.anibox.data.repository

import com.example.anibox.core.Endpoints
import com.example.anibox.core.SafeCall
import com.example.anibox.core.error.GeneralError
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.api.UserService
import com.example.anibox.di.AndroidKtorClient
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.data.remote.models.user.UserSignResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @AndroidKtorClient private val client: HttpClient,
    private val safeCall: SafeCall
): UserService {

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