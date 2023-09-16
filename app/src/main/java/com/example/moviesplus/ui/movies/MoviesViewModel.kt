package com.example.moviesplus.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.repository.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel(){

    private val moviesRepository = MoviesRepository()

    private val _movies:MutableLiveData<MoviesResponse> = MutableLiveData()
    val movies:LiveData<MoviesResponse> get() = _movies


    fun getMovies(){
        viewModelScope.launch {
            val response = moviesRepository.getMovies()
            _movies.value = response
        }
    }
}