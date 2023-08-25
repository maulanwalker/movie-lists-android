package com.example.movielists.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movielists.MovieListFragmentDirections
import com.example.movielists.R
import com.example.movielists.model.Movie

class MovieAdapter( 
    private val context: Context,
    private val dataset: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var filteredList : List<Movie> = dataset.toList()

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var imageView : ImageView = view.findViewById(R.id.movie_image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {
        filteredList = dataset.filter { movie ->
            context.resources.getString(movie.movieTitle).contains(query, true)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_movie, parent, false)

        return MovieViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = filteredList[position]
        holder.imageView.setImageResource(movie.movieImage)
        holder.imageView.setOnClickListener{
            val action = MovieListFragmentDirections
                .actionMovieListFragmentToMovieDetailFragment(movie.movieImage.toString())
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = filteredList.size
}