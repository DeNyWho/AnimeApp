package com.example.anibox.domain.use_cases.detail

import MangaDetailsDtoV4
import com.example.anibox.core.DispatchersProvider
import com.example.anibox.core.enum.ContentType
import com.example.anibox.core.exception.MyError
import com.example.anibox.core.wrapper.Resource
import com.example.anibox.data.remote.models.anime.dto.AnimeDetailsDtoV4
import com.example.anibox.data.remote.models.anime.dto.toContentDetails
import com.example.anibox.data.remote.models.common.ContentDetails
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.data.repository.MangaRepository
import com.example.anibox.presentation.detail.state.ContentDetailsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import toContentDetails
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val mangaRepository: MangaRepository,
    private val dispatchers: DispatchersProvider
) {
    operator fun invoke(contentType: String?, malId: Int?): Flow<ContentDetailsState> {
        return flow {
            emit(ContentDetailsState(isLoading = true))
            val type = ContentType.valueOf(contentType ?: "NoValue")
            val res = when(type) {
                ContentType.Anime -> animeRepository.getAnimeDetails(malId ?: 0)
                ContentType.Manga -> mangaRepository.getMangaDetails(malId ?: 0)
                else -> Resource.Error(MyError.UNKNOWN_ERROR)
            }

            val state = when (res) {
                is Resource.Success -> {
                    val data = resolveContentType(res.data)
                    ContentDetailsState(data)
                }
                is Resource.Error -> ContentDetailsState(error = res.message)
                is Resource.Loading -> ContentDetailsState(isLoading = true)
            }

            emit(state)

        }.flowOn(dispatchers.io)
    }

    private fun resolveContentType(data: Any?): ContentDetails? {
        if (data is AnimeDetailsDtoV4) {
            data.toContentDetails()
        } else if (data is MangaDetailsDtoV4) {
            data.toContentDetails()
        } else {
            null
        }
        val res = when (data) {
            is AnimeDetailsDtoV4 -> data.toContentDetails()
            is MangaDetailsDtoV4 -> data.toContentDetails()
            else -> null
        }


        Timber.d("res resolveContent $res")

        return res
    }
}