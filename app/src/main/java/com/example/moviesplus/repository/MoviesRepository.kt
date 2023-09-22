package com.example.moviesplus.repository

import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun discoverMovies(): MoviesResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.discoverMovies()
            response!!.body()!!
        }
    }
}