package com.abadisurio.cinematxt.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.vo.Resource

interface CinemaTXTDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: String): LiveData<Resource<MovieEntity>>
    fun setBookmarkMovie(movie: MovieEntity, state: Boolean)
    fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>>
    fun getAllTVShows(): LiveData<Resource<PagedList<TVShowEntity>>>
    fun getDetailTVShow(tvShowId: String): LiveData<Resource<TVShowEntity>>
    fun setBookmarkTVShow(tvShow: TVShowEntity, state: Boolean)
    fun getBookmarkedTVShows(): LiveData<PagedList<TVShowEntity>>

}