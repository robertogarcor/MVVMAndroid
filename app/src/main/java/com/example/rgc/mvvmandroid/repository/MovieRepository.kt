package com.example.rgc.mvvmandroid.repository

import com.example.rgc.mvvmandroid.datasource.Movie

interface MovieRepository {

    fun getMovies() : List<Movie>
    fun addMovie(movie: Movie)

}