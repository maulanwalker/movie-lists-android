package com.example.movielists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movielists.databinding.FragmentMovieDetailBinding
import com.example.movielists.model.MovieListViewModel


class MovieDetailFragment : Fragment() {
    private val sharedViewModel: MovieListViewModel by activityViewModels()
    private var _binding : FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var youtubeID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            youtubeID = it.getString("id_image").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            detailFragment = this@MovieDetailFragment
        }
        sharedViewModel.getDetailData(youtubeID)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun detailToPlay() {
        val action = MovieDetailFragmentDirections
            .actionMovieDetailFragmentToYoutubePlayerFragment(youtubeID)
        findNavController().navigate(action)
    }
}