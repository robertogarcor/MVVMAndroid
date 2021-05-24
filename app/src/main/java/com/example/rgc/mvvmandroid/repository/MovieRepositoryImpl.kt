package com.example.rgc.mvvmandroid.repository

import com.example.rgc.mvvmandroid.datasource.Movie

class MovieRepositoryImpl : MovieRepository {

    private val dataSource : MutableList<Movie> = ArrayList()

    init {
        dataSource.add(Movie("Aquellos que desean mi muerte","Suspense"))
        dataSource.add(Movie("Nadie (Nobody)","Acción"))
        dataSource.add(Movie("El Señor de los Anillos: El retorno del Rey","Fantasia"))
        dataSource.add(Movie("Chaos Walking","Ciencia ficción"))
        dataSource.add(Movie("Tiburón blanco","Acción"))
        dataSource.add(Movie("Godzilla vs Kong","Acción"))
        dataSource.add(Movie("¡Upsss 2! ¿Y ahora dónde está Noé?","Animación"))
        dataSource.add(Movie("Mortal Kombat","Acción"))
        dataSource.add(Movie("Ejército de los muertos","Acción"))
        dataSource.add(Movie("Raya y el último dragón","Animación"))
    }

    override fun getMovies() : List<Movie> {
        return dataSource
    }

    override fun addMovie(movie: Movie) {
        dataSource.add(movie)
    }
}