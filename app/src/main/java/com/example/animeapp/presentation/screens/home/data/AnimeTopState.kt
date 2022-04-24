package com.example.animeapp.presentation.screens.home.data

import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.domain.model.anime.AnimeTop

data class AnimeTopState(
    val data: List<AnimeTop> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)