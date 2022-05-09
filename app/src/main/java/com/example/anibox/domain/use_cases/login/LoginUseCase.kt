package com.example.anibox.domain.use_cases.login

import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.util.SessionManager
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.anibox.presenter.account.state.UserState
import timber.log.Timber
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(user: UserLoginDto): UserState {
        return when (val res = repository.getLogin (user)) {
            is Resource.Success -> {
                val userSql = repository.getSqlInfo(email = user.email)
                Timber.d(userSql.data.toString())
                sessionManager.updateSession(res.data!!.message!!, userSql.data!!.name  , user.email)
                val data = User(email = user.email, name = userSql.data.name, password = user.password)
                UserState(data = data, result = true)
            }
            is Resource.Error -> {
                UserState(error = Event(res.message), result = false)
            }
            is Resource.Loading -> UserState(isLoading = true)
        }
    }
}