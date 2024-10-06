package com.marwa.moviecomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class CastResponse(@SerializedName("cast") val results: List<Cast>)
data class Cast(
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String,

)

