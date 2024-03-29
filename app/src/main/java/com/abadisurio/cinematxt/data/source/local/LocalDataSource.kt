package com.abadisurio.cinematxt.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.local.room.CinemaTXTDao

class LocalDataSource private constructor(private val mCinemaTXTDao: CinemaTXTDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: CinemaTXTDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mCinemaTXTDao.getMovies()
    fun getBookmarkMovies(): DataSource.Factory<Int, MovieEntity> = mCinemaTXTDao.getBookmarkMovies()
    fun getDetailMovie(movieId: String): LiveData<MovieEntity> = mCinemaTXTDao.getDetailMovie(movieId)
    fun insertMovies(movies: List<MovieEntity>) = mCinemaTXTDao.insertMovies(movies)
    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCinemaTXTDao.updateMovie(movie)
    }

    // TV Show
    fun getAllTVShows(): DataSource.Factory<Int, TVShowEntity> = mCinemaTXTDao.getTVShows()
    fun getBookmarkTVShows(): DataSource.Factory<Int, TVShowEntity> = mCinemaTXTDao.getBookmarkTVShows()
    fun getDetailTVShow(tvShowId: String): LiveData<TVShowEntity> = mCinemaTXTDao.getDetailTVShow(tvShowId)
    fun insertTVShows(tvShows: List<TVShowEntity>) = mCinemaTXTDao.insertTVShows(tvShows)
    fun setTVShowBookmark(tvShow: TVShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCinemaTXTDao.updateTVShow(tvShow)
    }

}