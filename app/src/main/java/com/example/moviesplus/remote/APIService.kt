package com.example.moviesplus.remote

import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("discover/movie")
    suspend fun discoverMovies(): Response<MoviesResponse?>?
    @GET("discover/tv")
    suspend fun discoverTv(): Response<TvResponse?>?
}