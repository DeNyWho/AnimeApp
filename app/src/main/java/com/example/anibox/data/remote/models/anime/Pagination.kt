package com.example.anibox.data.remote.models.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("last_visible_page")
    val lastVisiblePage: Int = 0,
    @SerialName("has_next_page")
    val hasNextPage: Boolean = false
)