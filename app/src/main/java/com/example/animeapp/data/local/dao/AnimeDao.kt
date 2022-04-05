package com.example.animeapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.domain.model.Anime

@Dao
interface AnimeDao{

    @Query("SELECT * FROM anime_table ORDER BY id ASC")
    fun getAllAnime(): PagingSource<Int, Anime>

    @Query("SELECT * FROM anime_table WHERE id=:animeId")
    fun getSelectedAnime(animeId: Int): Anime

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnime(heroes: List<Anime>)

    @Query("DELETE FROM anime_table")
    suspend fun deleteAllAnime()

}