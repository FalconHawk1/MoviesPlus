package com.example.moviesplus.repository

import com.example.moviesplus.model.MoviesDetailsResponse
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDetailsRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun getMoviesDetails(movieId: Int): MoviesDetailsResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.moviesDetails(movieId)
            response!!.body()!!
        }
    }
}