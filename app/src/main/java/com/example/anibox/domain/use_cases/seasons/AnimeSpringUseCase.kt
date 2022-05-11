package com.example.anibox.domain.use_cases.seasons

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.toAnimeSeason
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.presentation.home.state.seasons.AnimeSpringState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimeSpringUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(year: Int, season: String): Flow<AnimeSpringState> {
        return flow {
            emit(AnimeSpringState(isLoading = true))

            val state = when (val res = repository.getSeasonAnime(year,season)) {
                is Resource.Success -> {
                    val data = res.data?.data?.map {
                        it.toAnimeSeason()
                    }.orEmpty()

                    AnimeSpringState(data)
                }
                is Resource.Error -> AnimeSpringState(error = Event(res.message))
                is Resource.Loading -> AnimeSpringState(isLoading = true)
            }
            emit(state)
        }.flowOn(dispatchers.io)
    }
}