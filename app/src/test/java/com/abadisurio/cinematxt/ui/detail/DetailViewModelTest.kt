package com.abadisurio.cinematxt.ui.detail

import com.abadisurio.cinematxt.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTVShow = DataDummy.generateDummyTVShows()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTVShow.tvShowId

    @Before
    fun moviesSetUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedShow(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedShow(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)
    }

    @Before
    fun tvShowSetUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedShow(tvShowId)
    }

    @Test
    fun getTVShow() {
        viewModel.setSelectedShow(dummyTVShow.tvShowId)
        val tvShowEntity = viewModel.getTVShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTVShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTVShow.imagePath, tvShowEntity.imagePath)
        assertEquals(dummyTVShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTVShow.title, tvShowEntity.title)
        assertEquals(dummyTVShow.description, tvShowEntity.description)
    }
}