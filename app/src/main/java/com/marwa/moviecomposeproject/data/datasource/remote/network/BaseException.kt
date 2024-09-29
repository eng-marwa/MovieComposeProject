package com.marwa.moviecomposeproject.data.datasource.remote.network

import com.google.gson.annotations.SerializedName

data class BaseException(
    @SerializedName("status_code") val code: Int,
    @SerializedName("status_message") val message: String
)