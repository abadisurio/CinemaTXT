package com.abadisurio.cinematxt.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    var movieId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var imagePath: String
): Parcelable
