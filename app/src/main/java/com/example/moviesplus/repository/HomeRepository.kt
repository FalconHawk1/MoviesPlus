package com.example.moviesplus.repository

import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.model.TvSeasonDetailsResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun discoverMovies(): MoviesResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.discoverMovies()
            response!!.body()!!
        }
    }

    suspend fun discoverTv(): TvResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.discoverTv()
            response!!.body()!!
        }
    }

    suspend fun topRatedTvShow(): TvResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.topRatedTvShow()
            response!!.body()!!
        }
    }

    suspend fun topRatedMovies(): MoviesResponse {
        return withContext(Dispatchers.IO){
            val response = apiService.topRatedMovies()
            response!!.body()!!
        }
    }
}