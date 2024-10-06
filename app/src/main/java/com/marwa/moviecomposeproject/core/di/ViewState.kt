package com.marwa.moviecomposeproject.core.di

import com.marwa.moviecomposeproject.data.datasource.remote.network.BaseException

open class ViewState<T>(val data:T? = null) {
    class Error<T>(val error:BaseException): ViewState<T>()
    class Empty<T>: ViewState<T>()
    class Loaded<T>(data:T): ViewState<T>(data)
    class Loading<T>: ViewState<T>()
}