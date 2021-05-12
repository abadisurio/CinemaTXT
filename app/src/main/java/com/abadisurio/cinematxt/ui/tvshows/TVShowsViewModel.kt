package com.abadisurio.cinematxt.ui.tvshows

import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.utils.DataDummy

class TVShowsViewModel: ViewModel() {
    fun getTVShows(): List<TVShowEntity> = DataDummy.generateDummyTVShows()
}