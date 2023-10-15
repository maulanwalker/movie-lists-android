package com.example.movielists

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.movielists.adapter.MovieAdapter
import com.example.movielists.databinding.FragmentMovieListBinding
import com.example.movielists.model.MovieListViewModel

class MovieListFragment : Fragment() {
    private val viewModel: MovieListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, MovieListViewModel.Factory(activity.application))
            .get(MovieListViewModel::class.java)
    }

//    private val sharedViewModel: MovieListViewModel by activityViewModels()
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = MovieAdapter()
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("SetTextI18n")
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.resultFilter.visibility = View.VISIBLE
                viewModel.changeTextSearch("$query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Called when the user changes the text in the search box
                // Handle text changes here
                binding.resultFilter.visibility = View.GONE
                viewModel.changeTextSearch("")
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}