package com.argahutama.compose.data.di

import android.content.Context
import com.argahutama.compose.data.repository.MovieRepositoryImpl
import com.argahutama.compose.data.source.remote.MovieService
import com.argahutama.compose.data.util.Constants
import com.argahutama.compose.domain.repository.MovieRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }

    @Singleton
    @Provides
    fun provideClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .cache(cache)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client).build()

    @Singleton
    @Provides
    fun provideMovieService(
        retrofit: Retrofit
    ): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService
    ): MovieRepository = MovieRepositoryImpl(movieService)
}