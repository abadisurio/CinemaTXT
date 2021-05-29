package com.abadisurio.cinematxt.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy
import com.abadisurio.cinematxt.vo.Resource
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TVShowEntity>>

    @Before
    fun moviesSetUp() {
        viewModel = DetailViewModel(cinemaTXTRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTVShow(tvShowId)
    }

    @Test
    fun getMovie() {

        val movie = MutableLiveData<Resource<MovieEntity>>()
        val resource = Resource.success(DataDummy.generateDummyMovies()[0])

        movie.value = resource

        `when`(cinemaTXTRepository.getDetailMovie(movieId)).thenReturn(movie)

        viewModel.detailMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movie.value)



//        val movieEntity = viewModel.detailMovie.value?.data
//        verify(cinemaTXTRepository).getDetailMovie(movieId)
//        assertNotNull(movieEntity)
//        assertEquals(dummyMovie.movieId, movieEntity?.movieId)
//        assertEquals(dummyMovie.imagePath, movieEntity?.imagePath)
//        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
//        assertEquals(dummyMovie.title, movieEntity?.title)
//        assertEquals(dummyMovie.description, movieEntity?.description)
//
//        viewModel.detailMovie.observeForever(movieObserver)
//        verify(movieObserver).onChanged(movie.value)

    }
    @Test
    fun getTVShow() {

        val tvShow = MutableLiveData<Resource<TVShowEntity>>()
        val resource = Resource.success(DataDummy.generateDummyTVShows()[0])

        tvShow.value = resource

        `when`(cinemaTXTRepository.getDetailTVShow(tvShowId)).thenReturn(tvShow)

        viewModel.detailTVShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShow.value)
    }
}