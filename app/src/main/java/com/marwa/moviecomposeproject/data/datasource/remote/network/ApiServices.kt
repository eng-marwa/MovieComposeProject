package com.marwa.moviecomposeproject.data.datasource.remote.network

import com.marwa.moviecomposeproject.data.model.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    companion object{
        fun createApiService(retrofit: Retrofit):ApiServices = retrofit.create(ApiServices::class.java)
    }

    @GET("movie/now_playing")
    suspend fun getNowShowingMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

}