package com.abadisurio.cinematxt.data

import com.abadisurio.cinematxt.data.source.remote.response.MovieResponse
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.utils.DataDummy
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class CinemaTXTRepositoryTest {
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val cinemaRepository = FakeCinemaTXTRepository(remote)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponse = DataDummy.generateRemoteDummyTVShows()

    @Test
    fun getAllMovies() {
        `when`(remote.getAllMovies()).thenReturn(movieResponses)
        val movieEntities = cinemaRepository.getAllMovies()
        verify(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }
    @Test
    fun getAllTVShows() {
        `when`(remote.getAllTVShows()).thenReturn(tvShowResponse)
        val tvShowEntities = cinemaRepository.getAllTVShows()
        verify(remote).getAllTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

}