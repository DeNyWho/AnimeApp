package com.example.anibox.domain.use_cases.seasons

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.data.repository.AnimeRepository
import javax.inject.Inject

class AnimeSeasonsUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
}