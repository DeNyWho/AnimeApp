package com.example.animeapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animeapp.util.SessionManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context = context)


    @Provides
    @Singleton
    @AndroidKtorClient
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            engine {
                connectTimeout = 15_000
                socketTimeout = 100_000
            }
            install(ContentNegotiation) {
                json()
            }
            install(Logging){
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }

    @Provides
    @Singleton
    @OkHttpKtorClient
    fun provideOkHttpClient(@ApplicationContext context: Context): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(
                    ChuckerInterceptor.Builder(context = context)
                        .collector(ChuckerCollector(context = context))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(enable = false)
                        .build()
                )
            }
        }
    }
}