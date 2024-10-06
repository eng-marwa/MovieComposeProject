package com.marwa.moviecomposeproject.domain.repository

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>>
//    suspend fun getLocalNowShowingMovies(): Flow<MovieEntity>

}