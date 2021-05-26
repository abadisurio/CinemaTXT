package com.abadisurio.cinematxt.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository

class MoviesViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = cinemaTXTRepository.getAllMovies()
}