package com.abadisurio.cinematxt.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTVShow = DataDummy.generateDummyTVShows()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTVShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cinemaTXTRepository: CinemaTXTRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TVShowEntity>

    @Before
    fun moviesSetUp() {
        viewModel = DetailViewModel(cinemaTXTRepository)
        viewModel.setSelectedShow(movieId)
    }

    @Test
    fun getMovie() {

        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(cinemaTXTRepository.getDetailMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(cinemaTXTRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)

    }

    @Before
    fun tvShowSetUp() {
        viewModel = DetailViewModel(cinemaTXTRepository)
        viewModel.setSelectedShow(tvShowId)
    }

    @Test
    fun getTVShow() {
        val tvShow = MutableLiveData<TVShowEntity>()
        tvShow.value = dummyTVShow

        `when`(cinemaTXTRepository.getDetailTVShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTVShow().value as TVShowEntity
        verify(cinemaTXTRepository).getDetailTVShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTVShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTVShow.imagePath, tvShowEntity.imagePath)
        assertEquals(dummyTVShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTVShow.title, tvShowEntity.title)
        assertEquals(dummyTVShow.description, tvShowEntity.description)

        viewModel.getTVShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTVShow)
    }
}