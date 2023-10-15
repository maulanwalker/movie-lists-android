package com.example.movielists.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.movielists.database.DatabaseMovie
import com.example.movielists.database.MoviesDatabase
import com.example.movielists.database.asDomainModel
import com.example.movielists.response.ResultItem
import com.example.movielists.retrofit.MovieListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val database: MoviesDatabase) {
    val movies: LiveData<List<ResultItem>> = database.movieDao.getMovies().map {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val movies = MovieListApi.retrofitService.getMovies()
            val movielist: List<DatabaseMovie> = movies.map {
                DatabaseMovie(id = it.id, url = it.imageLink, title = it.title) }
            database.movieDao.insertAll(movielist)
        }
    }
}