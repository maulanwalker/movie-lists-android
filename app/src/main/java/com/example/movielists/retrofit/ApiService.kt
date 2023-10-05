package com.example.movielists.retrofit

import com.example.movielists.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/data")
    fun getMovies(): Call<MovieListResponse>
}