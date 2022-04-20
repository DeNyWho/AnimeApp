package com.example.animeapp.domain.use_cases.login.save_onlogin

import com.example.animeapp.data.repository.Repository

class SaveOnLoginUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnLoginState(completed)
    }
}