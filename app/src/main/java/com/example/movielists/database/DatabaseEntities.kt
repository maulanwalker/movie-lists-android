package com.example.movielists.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielists.response.ResultItem

@Entity
data class DatabaseMovie constructor(
    @PrimaryKey val id: String,
    val url: String,
    val title: String
)

fun List<DatabaseMovie>.asDomainModel() : List<ResultItem> {
    return map {
        ResultItem(
            imageLink = it.url,
            id = it.id,
            title = it.title)
    }
}