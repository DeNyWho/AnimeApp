package com.example.anibox.data.remote.models.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Producer(
    @SerialName("mal_id")
    val malId: Int = 0,
    @SerialName("type")
    val type: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)