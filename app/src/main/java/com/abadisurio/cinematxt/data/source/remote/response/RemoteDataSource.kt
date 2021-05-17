package com.abadisurio.cinematxt.data.source.remote.response

import android.os.Handler
import android.os.Looper
import com.abadisurio.cinematxt.data.source.remote.ApiService
import com.abadisurio.cinematxt.data.source.remote.ApiService.Companion.API_KEY
import com.abadisurio.cinematxt.data.source.remote.ApiService.Companion.BASE_URL
import com.abadisurio.cinematxt.data.source.remote.ApiService.Companion.LANGUAGE_PREF
import com.abadisurio.cinematxt.data.source.remote.ApiService.Companion.PAGE
import com.abadisurio.cinematxt.utils.EspressoIdlingResource
import com.abadisurio.cinematxt.utils.JsonHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    private fun getApiService(): ApiService {
        val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getAllMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getAllTVShows(callback: LoadTVShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTVShowsReceived(jsonHelper.loadTVShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getPopularMovies():Call<ApiPopoularMoviesResponse> = getApiService().getPopularMovies(API_KEY, LANGUAGE_PREF, PAGE)
    fun getDetailMovie(showId: String):Call<ApiDetailMovieResponse> = getApiService().getDetailMovie(showId, API_KEY, LANGUAGE_PREF, PAGE)
    fun getPopularTVShows():Call<ApiPopoularTVShowsResponse> = getApiService().getPopularTVShows(API_KEY, LANGUAGE_PREF, PAGE)
    fun getDetailTVShow(showId: String):Call<ApiDetailTVShowResponse> = getApiService().getDetailTVShow(showId, API_KEY, LANGUAGE_PREF, PAGE)

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTVShowsCallback {
        fun onAllTVShowsReceived(tvShowResponse: List<TVShowResponse>)
    }

}