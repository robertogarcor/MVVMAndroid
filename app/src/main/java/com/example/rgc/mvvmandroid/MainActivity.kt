package com.example.rgc.mvvmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rgc.mvvmandroid.databinding.ActivityMainBinding
import com.example.rgc.mvvmandroid.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.progressVisibility.observe(this, Observer { visible ->
            binding.progressBar.visibility = if(visible) View.VISIBLE else View.INVISIBLE
        })

        viewModel.textViewListMovies.observe(this, Observer { movie ->
            binding.textViewListMovies.append(movie)
        })

        viewModel.message.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.editTextTitleMovie.observe(this, Observer { text ->
            binding.editTextTitleMovie.setText(text)
        })

        viewModel.editTextTypeMovie.observe(this, Observer { text ->
            binding.editTextTypeMovie.setText(text)
        })

        with(binding) {
            buttonAddMovie.setOnClickListener {
                viewModel.onButtonClicked(editTextTitleMovie.text.toString(),
                                            editTextTypeMovie.text.toString())
            }
        }

    }
}