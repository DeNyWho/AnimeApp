package com.example.anibox.data.remote.api

import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.manga.dto.MangaDetailsResponse
import com.example.anibox.data.remote.models.manga.dto.MangaTopResponse

interface MangaService {

    suspend fun getTopManga(page: Int): Resource<MangaTopResponse>

    suspend fun getMangaDetails(malId: Int): Resource<MangaDetailsResponse>

}