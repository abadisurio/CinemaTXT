package com.abadisurio.cinematxt.data.source.local.entity

data class MovieEntity(
    var movieId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var imagePath: String
)