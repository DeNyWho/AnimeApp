package com.example.anibox.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anibox.data.local.dao.AnimeDao
import com.example.anibox.domain.model.Anime

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