package com.example.animeapp.data.repository

import com.example.animeapp.core.SafeCall
import com.example.animeapp.data.remote.api.AnimeService
import com.example.animeapp.di.AndroidKtorClient
import io.ktor.client.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    @AndroidKtorClient private val client: HttpClient,
    private val safeCall: SafeCall
): AnimeService {


}