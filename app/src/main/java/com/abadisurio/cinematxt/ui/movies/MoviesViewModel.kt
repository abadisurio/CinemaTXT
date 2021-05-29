package com.abadisurio.cinematxt.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.vo.Resource

class MoviesViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = cinemaTXTRepository.getAllMovies()
    fun getBookmarkMovies(): LiveData<PagedList<MovieEntity>> = cinemaTXTRepository.getBookmarkedMovies()
}