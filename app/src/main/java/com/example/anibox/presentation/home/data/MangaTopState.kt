package com.example.anibox.presentation.home.data

import com.example.anibox.core.wrapper.Event
import com.example.anibox.domain.model.manga.details.MangaTop

data class MangaTopState(
    val data: List<MangaTop> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)