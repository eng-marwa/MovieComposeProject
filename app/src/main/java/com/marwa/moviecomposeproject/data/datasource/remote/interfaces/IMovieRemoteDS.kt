package com.marwa.moviecomposeproject.data.datasource.remote.interfaces

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface IMovieRemoteDS {
    suspend fun getNowShowingMovies():Flow<NetworkResource<MovieResponse>>
    suspend fun getPopularMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getMovieCast(movieId: Int): Flow<NetworkResource<CastResponse>>
}