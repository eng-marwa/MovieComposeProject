package com.marwa.moviecomposeproject.data.datasource.remote.network

open class NetworkResource<T>(
    val status: NetworkStatus,
    val data: T? = null,
    val error: BaseException? = null
)

class Loading():NetworkResource<Boolean>(NetworkStatus.Loading)
class  Success<T>(data: T):NetworkResource<T>(NetworkStatus.Success,data)
class  Failure(error: BaseException):NetworkResource<BaseException>(NetworkStatus.Failure,error)
