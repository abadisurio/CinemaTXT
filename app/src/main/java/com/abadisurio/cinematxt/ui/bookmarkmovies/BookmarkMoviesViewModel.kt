package com.abadisurio.cinematxt.ui.bookmarkmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository

class BookmarkMoviesViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getBookmarkMovies(): LiveData<PagedList<MovieEntity>> = cinemaTXTRepository.getBookmarkedMovies()
}