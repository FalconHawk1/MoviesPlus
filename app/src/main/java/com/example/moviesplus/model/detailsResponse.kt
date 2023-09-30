package com.example.moviesplus.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class ProductionCompany(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso3166_1: String,
    @SerializedName("name") val name: String
)

data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso639_1: String,
    @SerializedName("name") val name: String
)