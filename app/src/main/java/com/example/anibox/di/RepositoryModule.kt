package com.example.anibox.di

import android.content.Context
import com.example.anibox.core.DispatchersProvider
import com.example.anibox.data.repository.AnimeRepository
import com.example.anibox.data.repository.DataStoreOperationsImpl
import com.example.anibox.data.repository.Repository
import com.example.anibox.domain.repository.DataStoreOperations
import com.example.anibox.domain.use_cases.UseCases
import com.example.anibox.domain.use_cases.login.read_onlogin.ReadOnLoginUseCase
import com.example.anibox.domain.use_cases.mangaTop.GetMangaTopUseCase
import com.example.anibox.domain.use_cases.popularAnime.AnimePopularUseCase
import com.example.anibox.domain.use_cases.seasons.*
import com.example.anibox.domain.use_cases.splash.read_onboarding.ReadOnBoardingUseCase
import com.example.anibox.domain.use_cases.splash.save_onboarding.SaveOnBoardingUseCase
import com.example.anibox.domain.use_cases.topAnime.GetAnimeTopUseCase
import com.example.animeapp.domain.use_cases.login.save_onlogin.SaveOnLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository, animeRepository: AnimeRepository, dispatchers: DispatchersProvider): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            saveOnLoginUseCase = SaveOnLoginUseCase(repository = repository),
            readOnLoginUseCase = ReadOnLoginUseCase(repository = repository),
            animePopularUseCase = AnimePopularUseCase(repository = animeRepository,dispatchers = dispatchers),
            animeSeasonsUseCase = AnimeSeasonsUseCase(repository = animeRepository, dispatchers = dispatchers),
            animeAutumnUseCase = AnimeAutumnUseCase(repository = animeRepository, dispatchers = dispatchers),
            animeSummerUseCase = AnimeSummerUseCase(repository = animeRepository, dispatchers = dispatchers),
            animeSpringUseCase = AnimeSpringUseCase(repository = animeRepository, dispatchers = dispatchers),
            animeWinterUseCase = AnimeWinterUseCase(repository = animeRepository, dispatchers = dispatchers),
            animeTop = GetAnimeTopUseCase(repository = animeRepository, dispatchers = dispatchers),
            mangaTop = GetMangaTopUseCase(repository = animeRepository, dispatchers = dispatchers)
        )
    }
}