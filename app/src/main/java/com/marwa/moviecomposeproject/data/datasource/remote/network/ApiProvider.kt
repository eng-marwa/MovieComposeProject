package com.marwa.moviecomposeproject.data.datasource.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class ApiProvider {
    fun <T : Any> apiRequest(apiCall: suspend () -> Response<T>): Flow<NetworkResource<T>> {
        return flow {
            emit(NetworkResource.Loading<T>())
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(NetworkResource.Success(body))
                    } else {
                        emit(
                            NetworkResource.Failure(
                                error = BaseException(
                                    code = response.code(),
                                    message = "Response body is null"
                                )
                            )
                        )
                    }
                } else {
                    emit(
                        NetworkResource.Failure(
                            error = BaseException(response.code(), response.message())
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    NetworkResource.Failure(
                        error = BaseException(
                            500,
                            e.message ?: "Unknown error"
                        )
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}