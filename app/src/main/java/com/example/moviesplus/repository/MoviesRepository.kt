package com.example.moviesplus.repository

import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.remote.APIService
import com.example.moviesplus.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository {

    private val apiService = RetrofitClient.instance?.create(APIService::class.java)

    suspend fun getMovies(): MoviesResponse {
        return withContext(Dispatchers.IO){
            val response = apiService?.getMoviesByCategory("Bearer ${apyKey}")
            response!!.body()!!
        }
    }

    companion object {
        private const val apyKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNWNhOTJkODJjNGVmODFiYWQ0MzQ1OGQ2ZmRiYmU1YSIsInN1YiI6IjYzMWY1MDJmMGJiMDc2MDA4MDI0MDc0ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OY8W_wmxE-mHNR6vUvqV_3_UcurPUvs-w1C3vO0rsas"
    }
}