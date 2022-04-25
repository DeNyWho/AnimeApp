package com.example.animeapp.domain.use_cases.login

import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.presentation.screens.account.state.SignUpState
import com.example.animeapp.presentation.screens.account.state.UserState
import com.example.animeapp.util.SessionManager
import timber.log.Timber
import javax.inject.Inject

class SignUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(user: UserDto): SignUpState {
        return when (val res = repository.getSign (user)) {
            is Resource.Success -> {
                sessionManager.updateSession(res.message!!, user.name, user.email)
                Timber.d(res.message)
                val data = User(email = user.email, name = user.name, password = user.password)
                SignUpState(data = data, result = true)

            }
            is Resource.Error -> {
                Timber.d(res.message)
                SignUpState(error = Event(res.message), result = false)
            }
            is Resource.Loading -> SignUpState(isLoading = true)
        }
    }
}