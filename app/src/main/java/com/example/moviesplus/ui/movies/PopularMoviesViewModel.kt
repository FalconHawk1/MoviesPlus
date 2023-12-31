package com.example.moviesplus.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.repository.PopularMoviesRepository
import kotlinx.coroutines.launch

class PopularMoviesViewModel: ViewModel() {

    private val popularMoviesRepository = PopularMoviesRepository()

    private val _popularMovies: MutableLiveData<MoviesResponse> = MutableLiveData()

    val movies: LiveData<MoviesResponse> get() = _popularMovies


    fun getPopularMovies(){
        viewModelScope.launch {
            val response = popularMoviesRepository.popularMovies()
            _popularMovies.value = response
        }
    }
}