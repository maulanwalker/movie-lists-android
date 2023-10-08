package com.example.movielists.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielists.response.ResultItem
import com.example.movielists.retrofit.MovieListApi
import kotlinx.coroutines.launch

enum class MovieListApiStatus { LOADING, ERROR, DONE }

class MovieListViewModel : ViewModel() {
    var tempMovies : List<ResultItem> = listOf()
    private val _movies = MutableLiveData<List<ResultItem>>()
    val movies: LiveData<List<ResultItem>> = _movies

    private val _textSearch = MutableLiveData<String>()
    val textSearch: LiveData<String> = _textSearch

    private val _movie = MutableLiveData<ResultItem>()
    val movie: LiveData<ResultItem> = _movie

    private val _status = MutableLiveData<MovieListApiStatus>()
    val status: LiveData<MovieListApiStatus> = _status

    init {
        getData()
    }

    fun getDataFilter(keySearch: String) : List<ResultItem> {
        val tMovies = tempMovies.filter { movie ->
            movie.title.contains(keySearch, true)
        }
        return tMovies
    }

    internal fun changeTextSearch(text: String) {
        if(text != ""){
            _movies.value = getDataFilter(text)
            if(_movies.value!!.isNotEmpty()) {
                _textSearch.value = "Result for : $text"
            } else {
                _textSearch.value = "No data for $text"
            }
        } else {
            _textSearch.value = text
            _movies.value = tempMovies
        }
    }

    private fun getData() {
        viewModelScope.launch {
            _status.value = MovieListApiStatus.LOADING
            try {
                tempMovies = MovieListApi.retrofitService.getMovies()
                _movies.value = tempMovies
//                val client = ApiConfig.getApiService().getMovies()
//                client.enqueue(object : Callback<MovieListResponse> {
//                    override fun onResponse(
//                        call: Call<MovieListResponse>,
//                        response: Response<MovieListResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val responseBody = response.body()
//                            if (responseBody != null) {
//                                tempMovies = responseBody.result
//                                _movies.value = tempMovies
//                            }
//                        } else {
//                            Log.e("TAG", "onFailure: ${response.message()}")
//                        }
//                    }
//                    override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                        Log.e("TAG", "onFailure: ${t.message}")
//                    }
//                })
                _status.value = MovieListApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieListApiStatus.ERROR
                _movies.value = listOf()
            }
        }
    }

    fun getDetailData(imageID: String) {
        val result = movies.value?.find { it.id == imageID }
        _movie.value = result!!
    }
}