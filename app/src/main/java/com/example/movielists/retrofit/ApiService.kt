package com.example.movielists.retrofit

import com.example.movielists.response.ResultItem
import retrofit2.http.GET

interface ApiService {
    @GET("api/data")
    suspend fun getMovies(): List<ResultItem>
}