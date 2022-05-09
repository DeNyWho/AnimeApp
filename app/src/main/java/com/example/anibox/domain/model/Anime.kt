package com.example.anibox.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.anibox.util.Constants.ANIME_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ANIME_DATABASE_TABLE)
data class Anime(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val location: String,
    val category: String,
    val episodes: String
)