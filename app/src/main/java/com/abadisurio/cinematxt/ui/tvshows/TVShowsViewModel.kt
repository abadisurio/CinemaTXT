package com.abadisurio.cinematxt.ui.tvshows

import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy

class TVShowsViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
//    fun getTVShows(): List<TVShowEntity> = DataDummy.generateDummyTVShows()
    fun getTVShows(): List<TVShowEntity> = cinemaTXTRepository.getAllTVShows()
}