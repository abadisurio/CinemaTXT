package com.abadisurio.cinematxt.data.source

import androidx.lifecycle.LiveData
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity

interface CinemaTXTDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getDetailMovie(movieId: String): LiveData<MovieEntity>
//    fun getBookmarkedMovies(): LiveData<List<MovieEntity>>
    fun getDetailTVShow(tvShowId: String): LiveData<TVShowEntity>
    fun getAllTVShows(): LiveData<List<TVShowEntity>>
//    fun getBookmarkedTVShows(): LiveData<List<TVShowEntity>>

}