package com.example.moviesplus.repository

import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularTvRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun popularTvShow(): TvResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.popularTvShow()
            response!!.body()!!
        }
    }
}