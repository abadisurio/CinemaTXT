package com.abadisurio.cinematxt.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.remote.response.MovieResponse
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.data.source.remote.response.TVShowResponse

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
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(response.movieId,
                        response.title,
                        response.description,
                        response.releaseDate,
                        response.imagePath)
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getDetailMovie(movieId: String): LiveData<MovieEntity>{
        lateinit var movie: MovieEntity
        val movieResults = MutableLiveData<MovieEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                for (response in movieResponses) {
                    if(response.movieId == movieId){
                        Log.d("hehe", response.toString())
                        movie = MovieEntity(response.movieId,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.imagePath)
                    }
                }
                movieResults.postValue(movie)
            }
        })
        return movieResults
    }


    override fun getAllTVShows(): LiveData<List<TVShowEntity>>{
        val tvShowResults = MutableLiveData<List<TVShowEntity>>()
        remoteDataSource.getAllTVShows(object : RemoteDataSource.LoadTVShowsCallback {
            override fun onAllTVShowsReceived(tvShowResponses: List<TVShowResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TVShowEntity(response.tvShowId,
                        response.title,
                        response.description,
                        response.releaseDate,
                        response.imagePath)
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }

    override fun getDetailTVShow(tvShowId: String): LiveData<TVShowEntity>{
        lateinit var tvShow: TVShowEntity
        val tvShowResults = MutableLiveData<TVShowEntity>()
        remoteDataSource.getAllTVShows(object : RemoteDataSource.LoadTVShowsCallback {
            override fun onAllTVShowsReceived(tvShowResponses: List<TVShowResponse>) {
                for (response in tvShowResponses) {
                    if(response.tvShowId == tvShowId){
                        tvShow = TVShowEntity(response.tvShowId,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.imagePath)
                    }
                }
                tvShowResults.postValue(tvShow)
            }
        })
        return tvShowResults
    }

}