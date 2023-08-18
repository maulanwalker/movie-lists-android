package com.example.movielists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielists.adapter.MovieAdapter
import com.example.movielists.data.Datasource

class MainActivity : AppCompatActivity() {

    private lateinit var datasource: Datasource
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datasource = Datasource()
        movieAdapter = MovieAdapter(this, datasource.loadMovies())
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = movieAdapter
        recyclerView.setHasFixedSize(true)

        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieAdapter.filter(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Called when the user changes the text in the search box
                // Handle text changes here
                if(newText.toString().isEmpty()){
                    movieAdapter.filter(newText.orEmpty())
                }
                return true
            }
        })
    }
}