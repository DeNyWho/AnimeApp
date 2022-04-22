package com.example.animeapp.core

import com.example.animeapp.core.error.GeneralError
import com.example.animeapp.core.exception.MyError
import com.example.animeapp.core.wrapper.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class SafeCall {
    suspend inline operator fun <reified T: Any, reified U: Any> invoke(
        client: HttpClient,
        request: HttpRequestBuilder
    )
            : Resource<T> {
        return try {
            val res: HttpResponse = client.request(request)

            if (res.status.isSuccess()) {
                val body = res.body<T>()
                Resource.Success(body)
            } else {
                when (val error = res.body<U>()) {
                    is GeneralError -> Resource.Error(error.message)
                    else -> Resource.Error(MyError.UNKNOWN_ERROR)
                }

            }
        } catch (e: Exception) {
            when (e) {
                is ClientRequestException -> Resource.Error(e.message)
                is ConnectTimeoutException -> Resource.Error(e.message ?: MyError.UNKNOWN_ERROR)
                else -> Resource.Error(MyError.UNKNOWN_ERROR)
            }
        }
    }
}