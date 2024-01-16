package com.example.movieapp.di

import com.example.movieapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MovieModule::class])
interface MovieComponent {

    fun inject(mainActivity: MainActivity)
}