package com.abadisurio.cinematxt.data.source

import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity

interface CinemaTXTDataSource {

    fun getAllMovies(): List<MovieEntity>
    fun getDetailMovie(movieId: String): MovieEntity
    fun getBookmarkedMovies(): List<MovieEntity>
    fun getDetailTVShow(tvShowId: String): TVShowEntity
    fun getAllTVShows(): List<TVShowEntity>
    fun getBookmarkedTVShows(): List<TVShowEntity>

}