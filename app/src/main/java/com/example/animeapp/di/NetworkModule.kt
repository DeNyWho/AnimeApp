package com.example.animeapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.animeapp.core.DefaultDispatchers
import com.example.animeapp.core.DispatchersProvider
import com.example.animeapp.core.SafeCall
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
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDispatchersProvider(): DispatchersProvider {
        return DefaultDispatchers(
            default = Dispatchers.Default,
            main = Dispatchers.Main,
            io = Dispatchers.IO,
            mainImmediate = Dispatchers.Main,
            unconfined = Dispatchers.Unconfined
        )
    }

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

    @Provides
    @Singleton
    fun provideCall(): SafeCall {
        return SafeCall()
    }
}