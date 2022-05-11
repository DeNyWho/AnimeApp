package com.example.anibox.domain.use_cases.mangaTop

import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.wrapper.Event
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.manga.dto.toMangaTop
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.presentation.home.data.MangaTopState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMangaTopUseCase @Inject constructor(
    private val repository: AnimeRepository,
    private val dispatchers: DispatchersProvider
    ) {
        operator fun invoke(page: Int = 1): Flow<MangaTopState> {
            return flow {
                emit(MangaTopState(isLoading = true))

                val state = when (val res = repository.getTopManga (page)) {
                    is Resource.Success -> {
                        val data = res.data?.data?.map { it.toMangaTop() }.orEmpty()
                        MangaTopState(data)
                    }
                    is Resource.Error -> MangaTopState(error = Event(res.message))
                    is Resource.Loading -> MangaTopState(isLoading = true)
                }

                emit(state)
            }.flowOn(dispatchers.io)
        }
    }