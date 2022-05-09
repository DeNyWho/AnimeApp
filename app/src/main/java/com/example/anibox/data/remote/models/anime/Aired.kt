package com.example.anibox.data.remote.models.anime

import com.example.animeapp.data.remote.models.common.Prop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String? = null,
    @SerialName("to")
    val to: String? = null,
    @SerialName("prop")
    val prop: Prop = Prop()
)
