package com.example.anibox.domain.use_cases.splash.read_onboarding

import com.example.anibox.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase (
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}