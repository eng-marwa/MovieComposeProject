package com.marwa.moviecomposeproject

import android.app.Application
import com.marwa.moviecomposeproject.core.di.dataSourceModule
import com.marwa.moviecomposeproject.core.di.networkModule
import com.marwa.moviecomposeproject.core.di.repositoryModule
import com.marwa.moviecomposeproject.core.di.roomModule
import com.marwa.moviecomposeproject.core.di.useCaseModule
import com.marwa.moviecomposeproject.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI(this)
    }

    private fun initDI(app: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    dataSourceModule,
                    networkModule,
                    roomModule
                )
            )

        }
    }
}