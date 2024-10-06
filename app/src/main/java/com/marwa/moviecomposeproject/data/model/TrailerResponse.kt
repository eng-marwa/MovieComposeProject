package com.marwa.moviecomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class TrailerResponse(@SerializedName("results") val trailers: List<Trailer>)
data class Trailer(
    @SerializedName("key") val key: String,
    @SerializedName("type")    val type: String)


