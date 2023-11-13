package com.example.moviesplus.model

data class Resource<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null,
)
