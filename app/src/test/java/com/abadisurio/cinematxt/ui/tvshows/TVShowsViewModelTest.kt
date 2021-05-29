package com.abadisurio.cinematxt.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cinemaTXTRepository: CinemaTXTRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var bookmarkObserver: Observer<PagedList<TVShowEntity>>

    @Mock
    private lateinit var pagedListTVShow: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(cinemaTXTRepository)
    }

    @Test
    fun getTVShow() {

        val dummyTVShows = Resource.success(pagedListTVShow)
        Mockito.`when`(dummyTVShows.data?.size).thenReturn(11)

        val tvShow = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        tvShow.value = dummyTVShows

        Mockito.`when`(cinemaTXTRepository.getAllTVShows()).thenReturn(tvShow)

        val movieEntities = viewModel.getTVShows().value?.data
        Mockito.verify(cinemaTXTRepository).getAllTVShows()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities?.size)

        viewModel.getTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTVShows)
    }
    @Test
    fun getBookmarkMovies() {

        val dummyBookmarkTVShows = pagedListTVShow
        Mockito.`when`(dummyBookmarkTVShows.size).thenReturn(11)

        val tvShows = MutableLiveData<PagedList<TVShowEntity>>()
        tvShows.value = dummyBookmarkTVShows

        Mockito.`when`(cinemaTXTRepository.getBookmarkedTVShows()).thenReturn(tvShows)

        val tvShowEntities = viewModel.getBookmarkTVShows().value
        Mockito.verify(cinemaTXTRepository).getBookmarkedTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities?.size)

        viewModel.getBookmarkTVShows().observeForever(bookmarkObserver)
        Mockito.verify(bookmarkObserver).onChanged(dummyBookmarkTVShows)

    }
}