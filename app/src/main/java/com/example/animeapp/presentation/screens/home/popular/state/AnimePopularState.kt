package com.example.animeapp.presentation.screens.home.popular.state

import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.domain.model.anime.AnimeAiringPopular

data class AnimePopularState(
    val data: List<AnimeAiringPopular> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)
