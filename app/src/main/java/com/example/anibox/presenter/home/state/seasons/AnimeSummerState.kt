package com.example.anibox.presenter.home.state.seasons

import com.example.anibox.core.wrapper.Event
import com.example.anibox.domain.model.anime.AnimeSeason

data class AnimeSummerState(
    val data: List<AnimeSeason> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)