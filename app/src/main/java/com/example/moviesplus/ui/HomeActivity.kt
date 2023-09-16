package com.example.moviesplus.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.moviesplus.databinding.ActivityHomeBinding
import com.example.moviesplus.databinding.ActivityMainBinding
import com.example.moviesplus.ui.movies.MoviesViewModel

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
    }
}