package com.abadisurio.cinematxt.ui.movies

import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var cinemaTXTRepository: CinemaTXTRepository

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(cinemaTXTRepository)
    }

    @Test
    fun getCourses() {
        Mockito.`when`(cinemaTXTRepository.getAllMovies()).thenReturn(DataDummy.generateDummyMovies())
        val courseEntities = viewModel.getMovies()
        verify(cinemaTXTRepository).getAllMovies()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities.size)
    }
}