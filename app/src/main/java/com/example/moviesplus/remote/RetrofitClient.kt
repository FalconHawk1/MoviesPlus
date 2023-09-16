package com.example.moviesplus.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    var instance: Retrofit?=null
    get() {
        if (field == null) field = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return field
    }
}