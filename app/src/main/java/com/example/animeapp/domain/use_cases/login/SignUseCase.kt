package com.example.animeapp.domain.use_cases.login

import android.util.Log
import com.example.animeapp.core.DispatchersProvider
import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.toUser
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.presentation.screens.account.state.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(user: UserDto): Flow<UserState> {
        return flow {
            emit(UserState(isLoading = true))
            Log.d("RES","$repository.getSign (user)" )
            val state = when (val res = repository.getSign (user)) {
                is Resource.Success -> {
                    Log.d("RES","$res" )
                    val data = res.data?.data?.toUser()
                    UserState(data)
                }
                is Resource.Error -> UserState(
                    error = Event(res.message))
                is Resource.Loading -> UserState(isLoading = true)
            }

            emit(state)
        }.flowOn(dispatchers.io)
    }
}