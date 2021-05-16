package com.abadisurio.cinematxt.ui.tvshows

import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel

    @Mock
    private lateinit var cinemaTXTRepository: CinemaTXTRepository

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(cinemaTXTRepository)
    }

    @Test
    fun getCourses() {
        Mockito.`when`(cinemaTXTRepository.getAllTVShows()).thenReturn(DataDummy.generateDummyTVShows())
        val courseEntities = viewModel.getTVShows()
        Mockito.verify(cinemaTXTRepository).getAllTVShows()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities.size)
    }
}