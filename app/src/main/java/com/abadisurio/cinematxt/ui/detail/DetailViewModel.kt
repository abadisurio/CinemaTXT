package com.abadisurio.cinematxt.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository

class DetailViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    private lateinit var showId: String

    fun setSelectedShow(showId: String) {
        this.showId = showId
    }

    fun getMovie(): LiveData<MovieEntity> = cinemaTXTRepository.getDetailMovie(showId)
    fun getTVShow(): LiveData<TVShowEntity> = cinemaTXTRepository.getDetailTVShow(showId)

}