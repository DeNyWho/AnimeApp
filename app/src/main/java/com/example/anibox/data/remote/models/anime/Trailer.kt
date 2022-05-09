package com.example.anibox.data.remote.models.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("youtube_id")
    val youtubeId: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("embed_url")
    val embedUrl: String? = null
)