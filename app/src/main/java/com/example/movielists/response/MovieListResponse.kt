package com.example.movielists.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>
)

data class ResultItem(

	@field:SerializedName("imageLink")
	val imageLink: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
