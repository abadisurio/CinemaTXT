package com.abadisurio.cinematxt.data.source.remote
import com.abadisurio.cinematxt.data.source.remote.response.ApiPopoularMoviesResponse
import com.abadisurio.cinematxt.BuildConfig
import com.abadisurio.cinematxt.data.source.remote.response.ApiDetailMovieResponse
import com.abadisurio.cinematxt.data.source.remote.response.ApiDetailTVShowResponse
import com.abadisurio.cinematxt.data.source.remote.response.ApiPopoularTVShowsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = BuildConfig.BASE_URL
        const val LANGUAGE_PREF = "en-US"
        const val PAGE = 1
    }

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ApiPopoularMoviesResponse>

    @GET("3/movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") showId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ApiDetailMovieResponse>

    @GET("3/tv/popular")
    fun getPopularTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ApiPopoularTVShowsResponse>

    @GET("3/tv/{tvshow_id}")
    fun getDetailTVShow(
        @Path("tvshow_id") show: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ApiDetailTVShowResponse>
}