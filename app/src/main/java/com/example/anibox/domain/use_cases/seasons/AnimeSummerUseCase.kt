package com.example.anibox.domain.use_cases.seasons

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.toAnimeSeason
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.presentation.home.state.seasons.AnimeSummerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimeSummerUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(year: Int, season: String): Flow<AnimeSummerState> {
        return flow {
            emit(AnimeSummerState(isLoading = true))

            val state = when (val res = repository.getSeasonAnime(year,season)) {
                is Resource.Success -> {
                    val data = res.data?.data?.map {
                        it.toAnimeSeason()
                    }.orEmpty()

                    AnimeSummerState(data)
                }
                is Resource.Error -> AnimeSummerState(error = Event(res.message))
                is Resource.Loading -> AnimeSummerState(isLoading = true)
            }
            emit(state)
        }.flowOn(dispatchers.io)
    }

}