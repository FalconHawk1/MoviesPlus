package com.example.moviesplus.repository

import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun discoverTv(): TvResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.discoverTv()
            response!!.body()!!
        }
    }
}