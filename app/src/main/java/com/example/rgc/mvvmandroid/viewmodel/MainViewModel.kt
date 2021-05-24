package com.example.rgc.mvvmandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rgc.mvvmandroid.datasource.Movie
import com.example.rgc.mvvmandroid.repository.MovieRepository
import com.example.rgc.mvvmandroid.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"
    private val repository : MovieRepository = MovieRepositoryImpl()

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility : LiveData<Boolean> get() = _progressVisibility

    private val _textViewListMovies = MutableLiveData<String>()
    val textViewListMovies : LiveData<String> get() = _textViewListMovies

    private val _editTextTitleMovie = MutableLiveData<String>()
    val editTextTitleMovie : LiveData<String> get() = _editTextTitleMovie

    private val _editTextTypeMovie = MutableLiveData<String>()
    val editTextTypeMovie : LiveData<String> get() = _editTextTypeMovie

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    init {
        val listMovies = repository.getMovies()
        if(listMovies.isNotEmpty()) getListMovies() else _message.value = "No movies list"
    }

    fun getListMovies() {
        viewModelScope.launch {
            _progressVisibility.value = true
            val movies = withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                repository.getMovies()
            }
            for (movie in movies) {
                _textViewListMovies.value = "${movie.title} | ${movie.type} \n"
            }
            _progressVisibility.value = false
        }
    }


    fun onButtonClicked(titleMovie: String, typeMovie: String) {
        if (titleMovie.isNotEmpty() && typeMovie.isNotEmpty()) {
            val movie = Movie(titleMovie, typeMovie)
            viewModelScope.launch {
                _progressVisibility.value = true
                val movies = withContext(Dispatchers.IO) {
                    Thread.sleep(2000)
                    repository.addMovie(movie)
                    // Return list movies to log -> no need
                    repository.getMovies()
                }
                _textViewListMovies.value = "${movie.title} | ${movie.type} \n"
                _editTextTitleMovie.value = ""
                _editTextTypeMovie.value = ""
                _progressVisibility.value = false
                // Log list Movies -> no need
                for (movie in movies) {
                    Log.d(TAG,"${movie.title} | ${movie.type}")
                }
            }
        } else {
            _message.value = "Invalid movie data!. Try again."
        }
    }

}


