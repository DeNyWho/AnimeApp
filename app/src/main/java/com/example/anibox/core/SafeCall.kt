package com.example.anibox.core

import com.example.anibox.core.error.GeneralError
import com.example.anibox.core.exception.MyError
import com.example.anibox.core.wrapper.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.network.sockets.*

class SafeCall {
    suspend inline operator fun <reified T: Any, reified U: Any> invoke(
        client: HttpClient,
        request: HttpRequestBuilder
    )
            : Resource<T> {
        return try {
            val res = client.request<HttpResponse>(request)

            if (res.status.isSuccess()) {
                val body = res.receive<T>()
                Resource.Success(body)
            } else {
                when (val error = res.receive<U>()) {
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