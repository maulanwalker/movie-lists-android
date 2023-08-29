package com.example.movielists

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielists.adapter.MovieAdapter
import com.example.movielists.data.Datasource
import com.example.movielists.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var datasource: Datasource
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var resultFilter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        searchView = binding.searchView
        resultFilter = binding.resultFilter
        datasource = Datasource()
        movieAdapter = MovieAdapter(this.requireContext(), datasource.loadMovies())

        recyclerView.adapter = movieAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("SetTextI18n")
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieAdapter.filter(query.orEmpty())
                resultFilter.visibility = View.VISIBLE
                resultFilter.text = "Result for : \"${query.toString()}\""
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Called when the user changes the text in the search box
                // Handle text changes here
                if(newText.toString().isEmpty()){
                    movieAdapter.filter(newText.orEmpty())
                    resultFilter.visibility = View.GONE
                    resultFilter.text = ""
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}