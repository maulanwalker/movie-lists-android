package com.example.movielists.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes val videoName: Int,
    @DrawableRes val videoResourceId: Int
)
