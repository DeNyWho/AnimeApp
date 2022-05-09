package com.example.anibox.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.anibox.core.DefaultDispatchers
import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.SafeCall
import com.example.anibox.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
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

//    @Singleton
//    @Provides
//    fun provideGson() = Gson()

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
                // this: AndroidEngineConfig
                connectTimeout = 15_000
                socketTimeout = 100_000
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(Logging) {
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

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }

    @Provides
    @Singleton
    fun provideCall(): SafeCall {
        return SafeCall()
    }
}