package com.abadisurio.cinematxt.ui.movies

import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy

class MoviesViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
//    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
    fun getMovies(): List<MovieEntity> = cinemaTXTRepository.getAllMovies()
}