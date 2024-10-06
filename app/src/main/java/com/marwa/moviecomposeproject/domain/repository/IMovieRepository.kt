package com.marwa.moviecomposeproject.domain.repository

import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getRemoteNowShowingMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getRemotePopularMovies(): Flow<NetworkResource<MovieResponse>>
    suspend fun getLocalNowShowingMovies(type:String): Flow<MovieEntity>
    suspend fun getLocalPopularMovies(type: String): Flow<MovieEntity>
    suspend fun saveMovie(movieEntity: MovieEntity): Long

}