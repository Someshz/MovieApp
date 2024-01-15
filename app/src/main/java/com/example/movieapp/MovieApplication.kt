package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.DaggerMovieComponent
import com.example.movieapp.di.MovieComponent

class MovieApplication : Application() {

    lateinit var component: MovieComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMovieComponent.builder().build()
    }

}