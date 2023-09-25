package com.example.moviesplus.ui.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.repository.PopularMoviesRepository
import com.example.moviesplus.repository.PopularTvRepository
import kotlinx.coroutines.launch

class PopularTvViewModel: ViewModel() {

    private val popularTvRepository = PopularTvRepository()

    private val _popularTv: MutableLiveData<TvResponse> = MutableLiveData()

    val tv: LiveData<TvResponse> get() = _popularTv


    fun getPopularMovies(){
        viewModelScope.launch {
            val response = popularTvRepository.popularTvShow()
            _popularTv.value = response

            Log.d("TAGI", _popularTv.value.toString())
        }
    }
}