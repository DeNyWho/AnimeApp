package com.example.anibox.presenter.home.state.popular

import com.example.anibox.core.wrapper.Event
import com.example.anibox.domain.model.anime.AnimeAiringPopular

data class AnimePopularState(
    val data: List<AnimeAiringPopular> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)
