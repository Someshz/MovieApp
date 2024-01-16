package com.example.movieapp.api

import com.example.movieapp.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getMovieFromAPI(
        @Query("api_key") api_Key: String,
        @Query("page") page: Int
    ): MovieList
}