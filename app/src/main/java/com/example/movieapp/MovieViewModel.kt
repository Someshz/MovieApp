package com.example.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.repository.MovieRepository

class MovieViewModel(val movieRepository: MovieRepository) : ViewModel() {

    val list = movieRepository.getMovieList().cachedIn(viewModelScope)

}