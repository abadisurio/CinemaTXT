package com.abadisurio.cinematxt.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.vo.Resource

class TVShowsViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getTVShows(): LiveData<Resource<PagedList<TVShowEntity>>> = cinemaTXTRepository.getAllTVShows()
}