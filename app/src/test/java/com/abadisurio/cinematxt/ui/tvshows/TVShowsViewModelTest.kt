package com.abadisurio.cinematxt.ui.tvshows

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TVShowsViewModelTest {

    private lateinit var viewModel: TVShowsViewModel
    @Before
    fun setUp() {
        viewModel = TVShowsViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getTVShows()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities.size)
    }
}