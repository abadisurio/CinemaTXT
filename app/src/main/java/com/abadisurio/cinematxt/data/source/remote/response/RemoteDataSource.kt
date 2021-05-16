package com.abadisurio.cinematxt.data.source.remote.response

import android.os.Handler
import android.os.Looper
import com.abadisurio.cinematxt.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback){
        handler.postDelayed({callback.onAllMoviesReceived(jsonHelper.loadMovies())}, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getAllTVShows(callback: LoadTVShowsCallback) {
        handler.postDelayed({callback.onAllTVShowsReceived(jsonHelper.loadTVShows())}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTVShowsCallback {
        fun onAllTVShowsReceived(tvShowResponse: List<TVShowResponse>)
    }

}