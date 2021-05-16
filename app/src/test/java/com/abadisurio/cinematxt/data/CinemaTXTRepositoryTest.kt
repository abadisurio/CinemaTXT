package com.abadisurio.cinematxt.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.utils.DataDummy
import com.abadisurio.cinematxt.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Rule

class CinemaTXTRepositoryTest {
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val cinemaRepository = FakeCinemaTXTRepository(remote)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponse = DataDummy.generateRemoteDummyTVShows()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getAllMovies() {

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(cinemaRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }
    @Test
    fun getAllTVShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVShowsCallback)
                .onAllTVShowsReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTVShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(cinemaRepository.getAllTVShows())
        verify(remote).getAllTVShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

}