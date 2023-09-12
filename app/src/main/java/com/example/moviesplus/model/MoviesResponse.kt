package com.example.moviesplus.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") var page:Int,
    @SerializedName("results") var results:List<DiscoverMoviesList>,
    @SerializedName("total_pages") var total_pages:Int,
    @SerializedName("total_result") var total_result:Int)