package com.example.animeapp.data.remote.models.manga

import com.example.animeapp.data.remote.models.common.Prop
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Published(
    @SerialName("from")
    val from: String = "",
    @SerialName("to")
    val to: String = "",
    @SerialName("prop")
    val prop: Prop = Prop(),
    @SerialName("string")
    val string: String = ""
)
