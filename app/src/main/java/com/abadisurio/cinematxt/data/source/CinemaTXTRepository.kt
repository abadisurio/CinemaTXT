package com.abadisurio.cinematxt.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abadisurio.cinematxt.BuildConfig
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.remote.response.*
import com.abadisurio.cinematxt.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CinemaTXTRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CinemaTXTDataSource {

    companion object {
        @Volatile
        private var instance: CinemaTXTRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CinemaTXTRepository =
            instance ?: synchronized(this) {
                instance ?: CinemaTXTRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>>{
        EspressoIdlingResource.increment()
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getPopularMovies().enqueue(object : Callback<ApiPopoularMoviesResponse>{
            override fun onResponse(call: Call<ApiPopoularMoviesResponse>, response: Response<ApiPopoularMoviesResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { callback ->
                        val movieList = arrayListOf<MovieEntity>()
                        for (result in callback.results){
                            movieList.add(
                                MovieEntity(
                                    result.id.toString(),
                                    result.originalTitle,
                                    result.overview,
                                    result.releaseDate,
                                    BuildConfig.BASE_IMAGE_URL + result.posterPath,
                                )
                            )
                        }
                        movieResults.postValue(movieList)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<ApiPopoularMoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return movieResults
    }

    override fun getDetailMovie(movieId: String): LiveData<MovieEntity>{
        Log.d("idshow", movieId)
        lateinit var movie: MovieEntity
        val movieDetailResults = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailMovie(movieId).enqueue(object : Callback<ApiDetailMovieResponse>{
            override fun onResponse(call: Call<ApiDetailMovieResponse>, response: Response<ApiDetailMovieResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { callback ->
                        movie = MovieEntity(
                            callback.id.toString(),
                            callback.title,
                            callback.overview,
                            callback.releaseDate,
                            BuildConfig.BASE_IMAGE_URL + callback.posterPath,
                        )
                    }
                    movieDetailResults.postValue(movie)
                }
            }
            override fun onFailure(call: Call<ApiDetailMovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return movieDetailResults
    }

    override fun getAllTVShows(): LiveData<List<TVShowEntity>>{
        EspressoIdlingResource.increment()
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()
        remoteDataSource.getPopularTVShows().enqueue(object : Callback<ApiPopoularTVShowsResponse>{
            override fun onResponse(call: Call<ApiPopoularTVShowsResponse>, response: Response<ApiPopoularTVShowsResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { callback ->
                        val tvShowList = arrayListOf<TVShowEntity>()
                        Log.d("wkwk", callback.results.toString())
                        for (result in callback.results){
                            tvShowList.add(
                                TVShowEntity(
                                    result.id.toString(),
                                    result.name,
                                    result.overview,
                                    result.firstAirDate,
                                    BuildConfig.BASE_IMAGE_URL + result.posterPath,
                                )
                            )
                        }
                        tvShowResults.postValue(tvShowList)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<ApiPopoularTVShowsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return tvShowResults
    }

    override fun getDetailTVShow(tvShowId: String): LiveData<TVShowEntity>{
        lateinit var tvShow: TVShowEntity
        val tvShowDetailResults = MutableLiveData<TVShowEntity>()
        remoteDataSource.getDetailTVShow(tvShowId).enqueue(object : Callback<ApiDetailTVShowResponse>{
            override fun onResponse(call: Call<ApiDetailTVShowResponse>, response: Response<ApiDetailTVShowResponse>) {
                if(response.isSuccessful)
                    response.body()?.let { callback ->
                        tvShow = TVShowEntity(
                            callback.id.toString(),
                            callback.originalName,
                            callback.overview,
                            callback.firstAirDate,
                            BuildConfig.BASE_IMAGE_URL + callback.posterPath,
                        )
                    }
                tvShowDetailResults.postValue(tvShow)
            }
            override fun onFailure(call: Call<ApiDetailTVShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return tvShowDetailResults
    }

}