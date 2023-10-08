package com.example.movielists

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielists.adapter.MovieAdapter
import com.example.movielists.model.MovieListApiStatus
import com.example.movielists.response.ResultItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResultItem>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("movieListApiStatus")
fun bindStatus(statusImageView: ImageView, status: MovieListApiStatus) {
    when (status) {
        MovieListApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MovieListApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MovieListApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}