package com.abadisurio.cinematxt.data.source.remote.response

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abadisurio.cinematxt.data.source.remote.ApiResponse
// sengaja dikomen buat dipake nanti oke ;)
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

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>>{
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }
    fun getAllTVShows(): LiveData<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTVShows = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        handler.postDelayed({
            resultTVShows.value = ApiResponse.success(jsonHelper.loadTVShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTVShows
    }

//    fun getPopularMovies():Call<ApiPopoularMoviesResponse> = getApiService().getPopularMovies(API_KEY, LANGUAGE_PREF, PAGE)
//    fun getDetailMovie(showId: String):Call<ApiDetailMovieResponse> = getApiService().getDetailMovie(showId, API_KEY, LANGUAGE_PREF, PAGE)
    fun getDetailMovie(filmId: String): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadDetailMovies(filmId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }
    fun getPopularTVShows(): LiveData<ApiResponse<List<TVShowResponse>>>{
        EspressoIdlingResource.increment()
        val resultTVShows = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        handler.postDelayed({
            resultTVShows.value = ApiResponse.success(jsonHelper.loadTVShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTVShows
    }

//    fun getPopularTVShows():Call<ApiPopoularTVShowsResponse> = getApiService().getPopularTVShows(API_KEY, LANGUAGE_PREF, PAGE)
    fun getDetailTVShow(tvShowId: String): LiveData<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTVShow = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        handler.postDelayed({
            resultTVShow.value = ApiResponse.success(jsonHelper.loadDetailTVShows(tvShowId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTVShow
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTVShowsCallback {
        fun onAllTVShowsReceived(tvShowResponse: List<TVShowResponse>)
    }

}