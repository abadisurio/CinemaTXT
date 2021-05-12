package com.abadisurio.cinematxt.ui.movies

import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.utils.DataDummy

class MoviesViewModel: ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}