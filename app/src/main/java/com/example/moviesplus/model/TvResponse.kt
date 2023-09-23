package com.example.moviesplus.model

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page") var page:Int,
    @SerializedName("results") var results:List<DiscoverTvList>,
    @SerializedName("total_pages") var total_pages:Int,
    @SerializedName("total_result") var total_result:Int)