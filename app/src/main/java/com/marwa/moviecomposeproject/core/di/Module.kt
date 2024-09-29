package com.marwa.moviecomposeproject.core.di

import com.marwa.moviecomposeproject.data.datasource.remote.interfaces.IMovieRemoteDS
import com.marwa.moviecomposeproject.data.datasource.remote.network.ApiServices.Companion.createApiService
import com.marwa.moviecomposeproject.data.datasource.remote.network.AuthInterceptor
import com.marwa.moviecomposeproject.data.datasource.remote.network.RetrofitClient.provideOkHttpClient
import com.marwa.moviecomposeproject.data.datasource.remote.network.RetrofitClient.provideRetrofit
import com.marwa.moviecomposeproject.data.datasource.remote.remote_repository.MovieRemoteDSImpl
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import com.marwa.moviecomposeproject.domain.repository.impl.MovieRepositoryImpl
import com.marwa.moviecomposeproject.domain.usescase.GetNowShowingUseCase
import com.marwa.moviecomposeproject.presentation.movies.viewmodel.MovieViewModel
import com.marwa.moviecomposeproject.utils.MovieViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}

val useCaseModule = module {
    factory { GetNowShowingUseCase(get()) }
}
val repositoryModule = module {
    factory { MovieRepositoryImpl(get()) as IMovieRepository }
}

val dataSourceModule = module {
    factory { MovieRemoteDSImpl(get()) as IMovieRemoteDS }
}


val networkModule = module {
    single { AuthInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { createApiService(get()) }

}

