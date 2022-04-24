package com.example.animeapp.domain.use_cases.popularAnime

import com.example.animeapp.core.DispatchersProvider
import com.example.animeapp.core.wrapper.Event
import com.example.animeapp.core.wrapper.Resource
import com.example.animeapp.data.remote.models.anime.dto.toAnimeAiringPopular
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.presentation.screens.home.popular.state.AnimePopularState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class AnimePopularUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {

    operator fun invoke(): Flow<AnimePopularState> {
        return flow {
            emit(AnimePopularState(isLoading = true))

            val state = when (val res = repository.getPopularAnime()) {
                is Resource.Success -> {
                    val data = res.data?.data?.map {
                        it.toAnimeAiringPopular()
                    }.orEmpty()
                    Timber.d(res.data?.toString())

                    AnimePopularState(data)
                }
                is Resource.Error -> AnimePopularState(error = Event(res.message))
                is Resource.Loading -> AnimePopularState(isLoading = true)
            }
            emit(state)
        }.flowOn(dispatchers.io)
    }
}