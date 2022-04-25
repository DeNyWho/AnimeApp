package com.example.animeapp.domain.use_cases.login

import com.example.animeapp.core.DispatchersProvider
import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.toUser
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.presentation.screens.account.state.UserState
import com.example.animeapp.util.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class SignUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider,
    private val sessionManager: SessionManager
) {
    operator fun invoke(user: UserDto): Flow<UserState> {
        return flow {
            emit(UserState(isLoading = true))
            Timber.d("${repository.getSign (user)}")
            val state = when (val res = repository.getSign(user)) {
                is Resource.Success -> {
                    sessionManager.updateSession(res.message!!, res.data!!.data!!.name ?: "", res.data.data!!.email)
                    val data = res.data.data.toUser()
                    UserState(data)
                }
                is Resource.Error -> UserState(
                    error = Event(res.message)
                )
                is Resource.Loading -> UserState(isLoading = true)
            }
            Timber.d(repository.getSign (user).message)


            emit(state)
        }.flowOn(dispatchers.io)
    }
}