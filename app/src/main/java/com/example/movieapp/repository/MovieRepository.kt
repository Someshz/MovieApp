package com.example.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.movieapp.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val pagingSource: MoviePagingSource) {

    fun getMovieList() = Pager(
        config = PagingConfig(pageSize = 1, maxSize = 100),
        pagingSourceFactory = { pagingSource }
    ).liveData

}