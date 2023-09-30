package com.example.moviesplus.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesplus.model.TvDetailsResponse
import com.example.moviesplus.repository.TvDetailsRepository
import kotlinx.coroutines.launch

class TvDetailsViewModel : ViewModel(){

    private val tvDetailsRepository = TvDetailsRepository()

    private val _tvDetails: MutableLiveData<TvDetailsResponse> = MutableLiveData()
    val tvDetails: LiveData<TvDetailsResponse> get() = _tvDetails

    private val _tvId : MutableLiveData<Int> = MutableLiveData(0)
    val tvId: LiveData<Int> get() = _tvId

    fun setTvId(id: Int){
        _tvId.value = id
    }

    fun getTvDetails(){
        viewModelScope.launch {
            if(_tvId.value != null) {
                val response = tvDetailsRepository.getTvDetails(_tvId.value!!)
                _tvDetails.value = response

                Log.d("TAGI: Movies Details", _tvDetails.value.toString())
            }
        }
    }
}