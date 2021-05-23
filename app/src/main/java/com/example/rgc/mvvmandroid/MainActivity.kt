package com.example.rgc.mvvmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rgc.mvvmandroid.databinding.ActivityMainBinding
import com.example.rgc.mvvmandroid.repository.MovieRepositoryImpl

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = MovieRepositoryImpl()
        val movies = repository.getMovies()

        for (movie in movies) {
            binding.textViewListMovies.append("${movie.title} | ${movie.type} \n")
        }


    }
}