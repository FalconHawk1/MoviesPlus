package com.example.moviesplus.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){

    private val homeRepository = HomeRepository()

    private val _movies:MutableLiveData<MoviesResponse> = MutableLiveData()
    val movies:LiveData<MoviesResponse> get() = _movies

    private val _tv:MutableLiveData<TvResponse> = MutableLiveData()
    val tv:LiveData<TvResponse> get() = _tv

    private val _tvTopRated:MutableLiveData<TvResponse> = MutableLiveData()
    val tvTopRated:LiveData<TvResponse> get() = _tvTopRated

    private val _moviesTopRated:MutableLiveData<MoviesResponse> = MutableLiveData()
    val moviesTopRated:LiveData<MoviesResponse> get() = _moviesTopRated


    fun getMovies(){
        viewModelScope.launch {
            val response = homeRepository.discoverMovies()
            _movies.value = response
        }
    }
    fun getTv(){
        viewModelScope.launch {
            val response = homeRepository.discoverTv()
            _tv.value = response
        }
    }
    fun getTopRatedTv(){
        viewModelScope.launch {
            val response = homeRepository.topRatedTvShow()
            _tvTopRated.value = response
        }
    }
    fun getTopRatedMovies(){
        viewModelScope.launch {
            val response = homeRepository.topRatedMovies()
            _moviesTopRated.value = response
        }
    }
}