package com.marwa.moviecomposeproject.data.datasource.remote.remote_repository

import com.marwa.moviecomposeproject.BuildConfig
import com.marwa.moviecomposeproject.data.datasource.remote.interfaces.IMovieRemoteDS
import com.marwa.moviecomposeproject.data.datasource.remote.network.ApiProvider
import com.marwa.moviecomposeproject.data.datasource.remote.network.ApiServices
import com.marwa.moviecomposeproject.data.datasource.remote.network.NetworkResource
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

class MovieRemoteDSImpl(private val apiServices: ApiServices) : IMovieRemoteDS, ApiProvider() {
    override suspend fun getNowShowingMovies(): Flow<NetworkResource<MovieResponse>> =
        apiRequest { apiServices.getNowShowingMovies(BuildConfig.API_KEY) }

    override suspend fun getPopularMovies(): Flow<NetworkResource<MovieResponse>> = apiRequest {
        apiServices.getPopularMovies(BuildConfig.API_KEY)
    }

    override suspend fun getMovieCast(movieId: Int): Flow<NetworkResource<CastResponse>> =apiRequest {
        apiServices.getMovieCast(movieId, BuildConfig.API_KEY)
    }

}