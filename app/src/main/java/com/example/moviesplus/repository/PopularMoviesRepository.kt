package com.example.moviesplus.repository

import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMoviesRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun popularMovies(): MoviesResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.popularMovies()
            response!!.body()!!
        }
    }
}