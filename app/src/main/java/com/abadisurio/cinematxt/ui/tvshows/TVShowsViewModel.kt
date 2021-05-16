package com.abadisurio.cinematxt.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy

class TVShowsViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getTVShows(): LiveData<List<TVShowEntity>> = cinemaTXTRepository.getAllTVShows()
}