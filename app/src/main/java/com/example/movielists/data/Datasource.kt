package com.example.movielists.data

import com.example.movielists.R
import com.example.movielists.model.Movie

class Datasource {
    fun loadMovies(): List<Movie> {
        return listOf<Movie>(
            Movie(R.string.movie1, R.drawable.image1),
            Movie(R.string.movie2, R.drawable.image1),
            Movie(R.string.movie3, R.drawable.image1)
        )
    }
}