package com.example.animeapp.data.remote.models.manga

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Serialization(
    @SerialName("mal_id")
    val malId: Int = 0,
    @SerialName("type")
    val type: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)