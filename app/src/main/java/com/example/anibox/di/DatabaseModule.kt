package com.example.anibox.di

import android.content.Context
import androidx.room.Room
import com.example.anibox.data.local.AnimeDataBase
import com.example.anibox.domain.repository.LocalDataSource
import com.example.anibox.util.Constants.ANIME_DATABASE
import com.example.anibox.data.repository.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AnimeDataBase {
        return Room.databaseBuilder(
            context,
            AnimeDataBase::class.java,
            ANIME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(
        animeDataBase: AnimeDataBase
    ) = animeDataBase.animeDao()


    @Provides
    @Singleton
    fun provideLocalDataSource(
        dataBase: AnimeDataBase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            animeDataBase = dataBase
        )
    }


}