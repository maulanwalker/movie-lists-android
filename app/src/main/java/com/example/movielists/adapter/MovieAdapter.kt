package com.example.movielists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movielists.MovieListFragmentDirections
import com.example.movielists.databinding.ListMovieBinding
import com.example.movielists.response.ResultItem

class MovieAdapter : ListAdapter<ResultItem, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val binding: ListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultItem){
            binding.photo = movie
            binding.movieImage.setOnClickListener {
                val action = MovieListFragmentDirections
                    .actionMovieListFragmentToMovieDetailFragment(idImage = movie.id)
                binding.movieImage.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultItem>() {
            override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}