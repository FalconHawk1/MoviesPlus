package com.example.moviesplus.repository

import com.example.moviesplus.model.TvDetailsResponse
import com.example.moviesplus.model.TvSeasonDetailsResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvDetailsRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun getTvDetails(tvId: Int): TvDetailsResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.tvShowsDetails(tvId)
            response!!.body()!!
        }
    }

    suspend fun fetchTvShowSeasonDetails(id: Int, seasonNumber: Int): TvSeasonDetailsResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.fetchTvSeasonDetails(id, seasonNumber)
            response!!.body()!!
        }
    }
}