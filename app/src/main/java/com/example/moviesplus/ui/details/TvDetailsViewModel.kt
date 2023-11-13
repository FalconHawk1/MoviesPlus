package com.example.moviesplus.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.extensions.getInitialSeasonIndex
import com.example.moviesplus.model.TvDetailsResponse
import com.example.moviesplus.model.TvSeasonDetailsResponse
import com.example.moviesplus.repository.HomeRepository
import com.example.moviesplus.repository.TvDetailsRepository
import com.example.moviesplus.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvDetailsViewModel : ViewModel(){

    private val tvDetailsRepository = TvDetailsRepository()

    private val _tvDetails: MutableLiveData<TvDetailsResponse> = MutableLiveData()
    val tvDetails: LiveData<TvDetailsResponse> get() = _tvDetails

    private val _tvId : MutableLiveData<Int> = MutableLiveData(0)
    val tvId: LiveData<Int> get() = _tvId

    val selectedSeasonNameIndexPair = MutableLiveData<Pair<String, Int>>(null)

    val selectedSeasonDetails: MutableLiveData<Resource<TvSeasonDetailsResponse>> =
        MutableLiveData(Resource(false, null, null))

    fun setTvId(id: Int){
        _tvId.value = id
    }

    fun getTvDetails(){
        viewModelScope.launch {
            if(_tvId.value != null) {
                val response = tvDetailsRepository.getTvDetails(_tvId.value!!)
                _tvDetails.value = response
                val initialSeasonIndex = response.getInitialSeasonIndex()
                if (initialSeasonIndex != -1) {
                    val initialSeason = response.seasons[initialSeasonIndex]
                    val firstSeasonDetails =
                        tvDetailsRepository.fetchTvShowSeasonDetails(_tvId.value!!, initialSeason.seasonNumber)
                    selectedSeasonDetails.postValue(selectedSeasonDetails.value!!.copy(data = firstSeasonDetails))
                    selectedSeasonNameIndexPair.postValue(Pair(initialSeason.name,
                        initialSeasonIndex))
                }
            }
        }
    }

    fun fetchSeasonDetails(id: Int, seasonNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedSeasonDetails.postValue(selectedSeasonDetails.value!!.copy(isLoading = true))
            try {
                val response = tvDetailsRepository.fetchTvShowSeasonDetails(id, seasonNumber)
                selectedSeasonDetails.postValue(selectedSeasonDetails.value!!.copy(data = response))
            } catch (e: Exception) {
                selectedSeasonDetails.postValue(selectedSeasonDetails.value!!.copy(error = e.message))
            }
        }
    }
}