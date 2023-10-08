package com.example.movielists.response

import com.squareup.moshi.Json

//data class MovieListResponse(
//
//	@field:SerializedName("result")
//	val result: List<ResultItem>
//)

data class ResultItem(

	@Json(name="imageLink")
	val imageLink: String,

	@Json(name="id")
	val id: String,

	@Json(name="title")
	val title: String
)
