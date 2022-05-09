package com.example.anibox.domain.use_cases.seasons

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.toAnimeSeason
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.presenter.home.state.seasons.AnimeAutumnState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimeAutumnUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(year: Int, season: String): Flow<AnimeAutumnState> {
        return flow {
            emit(AnimeAutumnState(isLoading = true))
            delay(1500)

            val state = when (val res = repository.getSeasonAnime(year,season)) {
                is Resource.Success -> {
                    val data = res.data?.data?.map {
                        it.toAnimeSeason()
                    }.orEmpty()

                    AnimeAutumnState(data)
                }
                is Resource.Error -> AnimeAutumnState(error = Event(res.message))
                is Resource.Loading -> AnimeAutumnState(isLoading = true)
            }
            emit(state)
        }.flowOn(dispatchers.io)
    }

}