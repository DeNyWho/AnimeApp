package com.example.animeapp.data.local

import android.content.Context
import androidx.room.*
import com.example.animeapp.data.local.dao.AnimeDao
import com.example.animeapp.domain.model.Anime

@Database(entities = [Anime::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class AnimeDataBase: RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): AnimeDataBase {

            val databaseBuilder = if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AnimeDataBase::class.java)
            } else {
                Room.databaseBuilder(context, AnimeDataBase::class.java, "anime_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun animeDao(): AnimeDao

//    abstract fun animeRemoteKeysDao()
//
//    abstract fun mangaDao()
//
//    abstract fun mangaRemoteKeysDao()


}