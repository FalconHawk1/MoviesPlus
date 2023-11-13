package com.example.moviesplus.remote

import com.example.moviesplus.model.MoviesDetailsResponse
import com.example.moviesplus.model.MoviesResponse
import com.example.moviesplus.model.TvDetailsResponse
import com.example.moviesplus.model.TvResponse
import com.example.moviesplus.model.TvSeasonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("discover/movie")
    suspend fun discoverMovies(): Response<MoviesResponse?>?
    @GET("discover/tv")
    suspend fun discoverTv(): Response<TvResponse?>?
    @GET("movie/popular")
    suspend fun popularMovies(): Response<MoviesResponse?>?
    @GET("tv/popular")
    suspend fun popularTvShow(): Response<TvResponse?>?
    @GET("movie/{movieId}")
    suspend fun moviesDetails(@Path("movieId") movieId: Int): Response<MoviesDetailsResponse?>?
    @GET("tv/{series_id}")
    suspend fun tvShowsDetails(@Path("series_id") tvId: Int): Response<TvDetailsResponse?>?
    @GET("tv/top_rated")
    suspend fun topRatedTvShow(): Response<TvResponse?>?
    @GET("movie/top_rated")
    suspend fun topRatedMovies(): Response<MoviesResponse?>?
    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun fetchTvSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
    ): Response<TvSeasonDetailsResponse?>?
}