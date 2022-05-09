package com.example.animeapp.data.remote.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    @SerialName("mal_id")
    val malId: Int = 0,
    @SerialName("type")
    val type: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)

fun Theme.toGenre(): Genre {
    return Genre(malId, type, name, url)
}