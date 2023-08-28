package com.example.movielists.data

import com.example.movielists.R
import com.example.movielists.model.Movie

class Datasource {
    fun loadMovies(): List<Movie> {
        return listOf<Movie>(
            Movie(R.string.movie1, R.drawable.image1, "dG91B3hHyY4"),
            Movie(R.string.movie2, R.drawable.image2, "pBk4NYhWNMM"),
            Movie(R.string.movie3, R.drawable.image3, "uYPbbksJxIg")
        )
    }

    fun getMovie(listMovie: List<Movie>, imageID: Int) : Movie? {
        for(movie in listMovie) {
            if(movie.movieImage == imageID) {
                return movie
            }
        }
        return null
    }
}
