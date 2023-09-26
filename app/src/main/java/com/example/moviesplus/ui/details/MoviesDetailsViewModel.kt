package com.example.moviesplus.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesDetailsResponse
import com.example.moviesplus.repository.MoviesDetailsRepository
import kotlinx.coroutines.launch

class MoviesDetailsViewModel : ViewModel(){

    private val moviesDetailsRepository = MoviesDetailsRepository()

    private val _moviesDetails: MutableLiveData<MoviesDetailsResponse> = MutableLiveData()
    val moviesDetails: LiveData<MoviesDetailsResponse> get() = _moviesDetails

    private val _movieId : MutableLiveData<Int> = MutableLiveData(0)
    val movieId: LiveData<Int> get() = _movieId

    fun setMovieId(id: Int){
        _movieId.value = id
    }

    fun getMoviesDetails(){
        viewModelScope.launch {
            if(_movieId.value != null) {
                val response = moviesDetailsRepository.getMoviesDetails(_movieId.value!!)
                _moviesDetails.value = response

                Log.d("TAGI: Movies Details", _moviesDetails.value.toString())
            }
        }
    }
}