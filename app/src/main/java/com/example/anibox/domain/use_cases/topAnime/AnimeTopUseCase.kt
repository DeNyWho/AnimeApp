package com.example.anibox.domain.use_cases.topAnime

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.toAnimeTop
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.presenter.home.data.AnimeTopState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAnimeTopUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(page: Int = 1): Flow<AnimeTopState> {
        return flow {
            emit(AnimeTopState(isLoading = true))

            val state = when (val res = repository.getTopAnime (page)) {
                is Resource.Success -> {
                    val data = res.data?.data?.map { it.toAnimeTop() }.orEmpty()
                    AnimeTopState(data)
                }
                is Resource.Error -> AnimeTopState(error = Event(res.message))
                is Resource.Loading -> AnimeTopState(isLoading = true)
            }

            emit(state)
        }.flowOn(dispatchers.io)
    }
}