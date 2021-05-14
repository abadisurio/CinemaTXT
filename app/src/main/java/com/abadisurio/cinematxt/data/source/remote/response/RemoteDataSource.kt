package com.abadisurio.cinematxt.data.source.remote.response

import com.abadisurio.cinematxt.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovies()
    fun getAllTVShows(): List<TVShowResponse> = jsonHelper.loadTVShows()

}