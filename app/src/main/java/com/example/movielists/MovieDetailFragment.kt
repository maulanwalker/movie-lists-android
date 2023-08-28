package com.example.movielists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.movielists.data.Datasource
import com.example.movielists.databinding.FragmentMovieDetailBinding
import com.example.movielists.model.Movie


class MovieDetailFragment : Fragment() {
    private var _binding : FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageID : String
    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageID = it.getString("id_image").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        movie = Datasource().getMovie(Datasource().loadMovies(), imageID.toInt())!!
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image = binding.movieImage
        val title = binding.movieTitle
        val button = binding.youtubePlayerButton

        image.setImageResource(imageID.toInt())
        title.text = movie.movieTitle.let { context?.resources?.getText(movie.movieTitle) }.toString()

        button.setOnClickListener {
            val action = MovieDetailFragmentDirections
                .actionMovieDetailFragmentToYoutubePlayerFragment(movie.movieYoutubeTrailerID)
            button.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}