package com.example.animeapp.data.remote.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    @SerialName("from")
    val from: From = From(),
    @SerialName("to")
    val to: To = To(),
    @SerialName("string")
    val string: String = ""
)
