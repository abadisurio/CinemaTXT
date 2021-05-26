package com.abadisurio.cinematxt.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository

class DetailViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(showId: String) {
        this.movieId = showId
    }
    fun setSelectedTVShow(showId: String) {
        this.tvShowId = showId
    }

    fun getMovie(): LiveData<MovieEntity> = cinemaTXTRepository.getDetailMovie(movieId)
    fun getTVShow(): LiveData<TVShowEntity> = cinemaTXTRepository.getDetailTVShow(tvShowId)

}