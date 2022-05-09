package com.example.anibox.presenter.home.data

import com.example.anibox.core.wrapper.Event
import com.example.anibox.domain.model.anime.AnimeTop

data class AnimeTopState(
    val data: List<AnimeTop> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)