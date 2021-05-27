package com.abadisurio.cinematxt.ui.bookmarktvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository

class BookmarkTVShowsViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getBookmarkTVShows(): LiveData<PagedList<TVShowEntity>> = cinemaTXTRepository.getBookmarkedTVShows()
}