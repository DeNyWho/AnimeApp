package com.example.animeapp.presentation.screens.account.state

import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.data.remote.models.user.User

data class SignUpState(
    val data: User? = null,
    var message: String? = null,
    val result: Boolean = false,
    val isLoading: Boolean = false,
    val error: Event<String?> = Event(null)
)