package com.example.anibox.presenter.home.anime_popular.state

import com.example.anibox.core.wrapper.Event
import com.example.anibox.domain.model.anime.AnimeAiringPopular

data class AnimeAiringPopularState(
  val data: List<AnimeAiringPopular> = listOf(),
  val isLoading: Boolean = false,
  val error: Event<String?> = Event(null)
)
