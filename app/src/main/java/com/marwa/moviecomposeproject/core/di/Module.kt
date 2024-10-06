package com.marwa.moviecomposeproject.core.di

import androidx.lifecycle.SavedStateHandle
import com.marwa.moviecomposeproject.data.datasource.local.MovieDao
import com.marwa.moviecomposeproject.data.datasource.local.MovieDb
import com.marwa.moviecomposeproject.data.datasource.remote.interfaces.IMovieRemoteDS
import com.marwa.moviecomposeproject.data.datasource.remote.network.ApiServices.Companion.createApiService
import com.marwa.moviecomposeproject.data.datasource.remote.network.AuthInterceptor
import com.marwa.moviecomposeproject.data.datasource.remote.network.RetrofitClient.provideOkHttpClient
import com.marwa.moviecomposeproject.data.datasource.remote.network.RetrofitClient.provideRetrofit
import com.marwa.moviecomposeproject.data.datasource.remote.remote_repository.MovieRemoteDSImpl
import com.marwa.moviecomposeproject.domain.repository.IMovieRepository
import com.marwa.moviecomposeproject.domain.repository.impl.MovieRepositoryImpl
import com.marwa.moviecomposeproject.domain.usescase.GetNowShowingUseCase
import com.marwa.moviecomposeproject.domain.usescase.GetPopularUseCase
import com.marwa.moviecomposeproject.domain.usescase.GetSavedNowShowingUseCase
import com.marwa.moviecomposeproject.domain.usescase.GetSavedPopularUseCase
import com.marwa.moviecomposeproject.domain.usescase.SaveMovieUseCase
import com.marwa.moviecomposeproject.presentation.movies.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MovieViewModel> { (handle: SavedStateHandle) ->
        MovieViewModel(handle, get(), get(), get())
    }

}

val useCaseModule = module {
    factory { GetNowShowingUseCase(get()) }
    factory { GetPopularUseCase(get()) }
    factory { SaveMovieUseCase(get()) }
    factory { GetSavedPopularUseCase(get()) }
    factory { GetSavedNowShowingUseCase(get()) }
}
val repositoryModule = module {
    factory { MovieRepositoryImpl(get(), get()) as IMovieRepository }
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


val roomModule = module {
    fun provideMovieDao(movieDb: MovieDb): MovieDao {
        return movieDb.movieDao()
    }

    single { MovieDb.getDatabase(androidApplication()) }
    single { provideMovieDao(get()) }
}
