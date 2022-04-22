package com.example.animeapp.domain.model

import com.example.animeapp.data.remote.models.anime.*
import com.example.animeapp.data.remote.models.common.*
import com.example.animeapp.data.remote.models.manga.Author
import com.example.animeapp.data.remote.models.manga.Published
import com.example.animeapp.data.remote.models.manga.Serialization

data class ContentDetails(
    val malId: Int,
    val url: String,
    val images: Images = Images(),
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
    val titleSynonyms: List<String>,
    val type: String,
    val status: String,
    val score: Double,
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String,
    val background: String?,
    val genres: List<Genre>,
    val explicitGenres: List<ExplicitGenre> = listOf(),
    val themes: List<Theme> = listOf(),
    val demographics: List<Demographic> = listOf(),

//  Manga
    val chapters: Int? = null,
    val volumes: Int? = null,
    val isPublishing: Boolean? = null,
    val published: Published? = null,
    val authors: List<Author> = listOf(),
    val serializations: List<Serialization> = listOf(),

//  Anime
    val trailer: Trailer? = null,
    val source: String? = null,
    val episodes: Int? = null,
    val isAiring: Boolean? = false,
    val aired: Aired? = null,
    val duration: String? = null,
    val ageRating: String? = null,
    val season: String? = null,
    val year: Int? = null,
    val broadcast: Broadcast? = null,
    val producers: List<Producer> = listOf(),
    val licensors: List<Licensor> = listOf(),
    val studios: List<Studio> = listOf(),

    )
