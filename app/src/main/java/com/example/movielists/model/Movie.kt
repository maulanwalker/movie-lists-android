package com.example.movielists.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes val movieTitle: Int,
    @DrawableRes val movieImage: Int,
    val movieYoutubeTrailerID: String
)
