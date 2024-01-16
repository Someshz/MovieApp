package com.example.movieapp.di

import com.example.movieapp.Constants
import com.example.movieapp.api.MovieService
import com.example.movieapp.paging.MoviePagingSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MovieModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getMovieService(retrofit: Retrofit):MovieService{
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    fun getPagingSource(movieService: MovieService):MoviePagingSource{
        return MoviePagingSource(movieService)
    }

}