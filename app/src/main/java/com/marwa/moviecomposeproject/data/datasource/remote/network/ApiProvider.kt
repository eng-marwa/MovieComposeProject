package com.marwa.moviecomposeproject.data.datasource.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class ApiProvider {
    fun <T> apiRequest(apiCall: suspend () -> Response<T>): Flow<NetworkResource<T>> {
        return flow {
            emit(NetworkResource<T>(NetworkStatus.Loading))
            val response = apiCall()
            if (response.isSuccessful) {
                emit(NetworkResource(NetworkStatus.Success, response.body()))
            } else {
                println(
                    "ApiProvider: apiRequest: response.message(): ${
                        response.errorBody()?.string()
                    } , ${response.message()} "
                )
                emit(
                    NetworkResource(
                        NetworkStatus.Failure,
                        error = BaseException(response.code(), response.message())
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}