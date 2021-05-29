package com.abadisurio.cinematxt.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cinemaTXTRepository: CinemaTXTRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var bookmarkObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(cinemaTXTRepository)
    }

    @Test
    fun getMovies() {

        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(11)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(cinemaTXTRepository.getAllMovies()).thenReturn(movies)

        val movieEntities = viewModel.getMovies().value?.data
        verify(cinemaTXTRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }
    @Test
    fun getBookmarkMovies() {

        val dummyBookmarkMovies = pagedListMovie
        `when`(dummyBookmarkMovies.size).thenReturn(11)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyBookmarkMovies

        `when`(cinemaTXTRepository.getBookmarkedMovies()).thenReturn(movies)

        val movieEntities = viewModel.getBookmarkMovies().value
        verify(cinemaTXTRepository).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities?.size)

        viewModel.getBookmarkMovies().observeForever(bookmarkObserver)
        verify(bookmarkObserver).onChanged(dummyBookmarkMovies)

    }
}