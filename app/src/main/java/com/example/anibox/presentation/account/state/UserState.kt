package com.example.anibox.presentation.account.state

import com.example.anibox.core.wrapper.Event
import com.example.animeapp.data.remote.models.user.User

data class UserState(
    val data: User? = null,
    var message: String? = null,
    val result: Boolean = false,
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)