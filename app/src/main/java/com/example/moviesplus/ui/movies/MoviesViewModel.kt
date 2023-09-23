package com.example.moviesplus.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.repository.MoviesRepository
import com.example.moviesplus.repository.TvRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel(){

    private val moviesRepository = MoviesRepository()
    private val tvRepository = TvRepository()

    private val _movies:MutableLiveData<MoviesResponse> = MutableLiveData()
    private val _tv:MutableLiveData<TvResponse> = MutableLiveData()
    val movies:LiveData<MoviesResponse> get() = _movies
    val tv:LiveData<TvResponse> get() = _tv


    fun getMovies(){
        viewModelScope.launch {
            val response = moviesRepository.discoverMovies()
            _movies.value = response

            Log.d("TAGI", _movies.value.toString())
        }
    }
    fun getTv(){
        viewModelScope.launch {
            val response = tvRepository.discoverTv()
            _tv.value = response

            Log.d("TAGI", _tv.value.toString())
        }
    }
}