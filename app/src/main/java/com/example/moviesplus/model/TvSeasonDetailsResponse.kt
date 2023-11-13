package com.example.moviesplus.model

import com.google.gson.annotations.SerializedName

data class TvSeasonDetailsResponse(
    val id: Int,
    @SerializedName("season_number") val seasonNumber: Int,
    val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    val overview: String,
    @SerializedName("air_date") val airDate: String?,
    val episodes: List<Episode>,
)