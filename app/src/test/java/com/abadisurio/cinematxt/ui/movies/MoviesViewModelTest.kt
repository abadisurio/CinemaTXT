package com.abadisurio.cinematxt.ui.movies

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getMovies()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities.size)
    }
}