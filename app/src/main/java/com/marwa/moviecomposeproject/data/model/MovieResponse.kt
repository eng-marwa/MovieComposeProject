package com.marwa.moviecomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("results") val results: List<Movie>)
data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList()
)


