package com.marwa.moviecomposeproject.data.datasource.remote.network

sealed class NetworkResource<T>(
    val status: NetworkStatus,
    val data: T? = null,
    val error: BaseException? = null
) {
    class Loading<T> : NetworkResource<T>(NetworkStatus.Loading)
    class Success<T>(data: T) : NetworkResource<T>(NetworkStatus.Success, data)
    class Failure<T>(error: BaseException) : NetworkResource<T>(NetworkStatus.Failure, error =error)
}