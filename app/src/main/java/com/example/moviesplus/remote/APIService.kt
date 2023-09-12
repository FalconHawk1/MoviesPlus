package com.example.moviesplus.remote

import com.example.moviesplus.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("/3/discover/movie")
    fun getMoviesByCategory(@Url url:String): Response<MoviesResponse>
}