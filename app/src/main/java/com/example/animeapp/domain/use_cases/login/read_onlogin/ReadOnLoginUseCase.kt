package com.example.animeapp.domain.use_cases.login.read_onlogin

import com.example.animeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnLoginUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnLoginState()
    }
}