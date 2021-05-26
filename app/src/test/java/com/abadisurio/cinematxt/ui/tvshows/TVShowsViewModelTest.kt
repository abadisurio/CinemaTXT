package com.abadisurio.cinematxt.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy
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
    private lateinit var observer: Observer<List<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(cinemaTXTRepository)
    }

    @Test
    fun getTVShow() {

        val dummyTVShows = DataDummy.generateDummyTVShows()
        val tvShows = MutableLiveData<List<TVShowEntity>>()
        tvShows.value = dummyTVShows

        Mockito.`when`(cinemaTXTRepository.getAllTVShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTVShows().value
        Mockito.verify(cinemaTXTRepository).getAllTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities?.size)

        viewModel.getTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTVShows)
    }
}