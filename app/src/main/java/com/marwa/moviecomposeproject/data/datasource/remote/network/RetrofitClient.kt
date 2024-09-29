package com.marwa.moviecomposeproject.data.datasource.remote.network

import com.marwa.moviecomposeproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
private const val CONNECT_TIMEOUT = 30L

object RetrofitClient {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASED_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()


    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val reqBuilder = req.newBuilder().addHeader("Content-Type", "application/json")
//            .addHeader("Authorization", "Bearer ${prefs.token}")
//            .addHeader("Lang", "${prefs.lang}")
            req = reqBuilder.build()
        return chain.proceed(req)
    }

}