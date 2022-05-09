package com.example.anibox.presenter.home.data

import com.example.anibox.core.wrapper.Event
import com.example.animeapp.domain.model.ContentSearch

data class ContentSearchState(
    val data: List<ContentSearch> = listOf(),
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)