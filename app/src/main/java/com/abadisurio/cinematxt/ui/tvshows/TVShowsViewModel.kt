package com.abadisurio.cinematxt.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository

class TVShowsViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getTVShows(): LiveData<List<TVShowEntity>> = cinemaTXTRepository.getAllTVShows()
}