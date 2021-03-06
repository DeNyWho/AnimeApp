package com.example.anibox.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentSearch(
    val malId: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val synopsis: String,
    val type: String? = null,
    /* Optional based on ContentType */

    /* Anime */
    val isAiring: Boolean? = null,
    val rated: String? = null,
    val episodesCount: Int? = null,

    /*  Manga  */
    val isPublishing: Boolean? = null,
    val chapters: Int? = null,
    val volumes: Int? = null,

    val startDate: String?,
    val endDate: String?,
    val members: Int,
    val score: Double
): Parcelable