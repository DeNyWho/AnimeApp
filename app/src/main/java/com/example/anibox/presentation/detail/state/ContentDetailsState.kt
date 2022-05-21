package com.example.anibox.presentation.detail.state

import com.example.anibox.data.remote.models.common.ContentDetails

data class ContentDetailsState(
    val data: ContentDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)