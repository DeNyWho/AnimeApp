package com.example.animeapp.presentation.screens.account.state

import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.domain.model.anime.AnimeTop

data class UserState(
    val data: User? = null,
    val result: Boolean = false,
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)