package com.example.movielists

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movielists.adapter.MovieAdapter
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
    imgView.load(imgUrl)
}