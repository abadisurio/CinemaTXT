package com.abadisurio.cinematxt.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.abadisurio.cinematxt.data.source.local.LocalDataSource
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.utils.AppExecutors
import com.abadisurio.cinematxt.utils.DataDummy
import com.abadisurio.cinematxt.utils.LiveDataTestUtil
import com.abadisurio.cinematxt.utils.PagedListUtil
import com.abadisurio.cinematxt.vo.Resource
import org.junit.Assert.*
import org.junit.Test
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CinemaTXTRepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutors::class.java)

    private val cinemaRepository = FakeCinemaTXTRepository(remote, local, appExecutor)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponses = DataDummy.generateRemoteDummyTVShows()
    private val dummyMovie = movieResponses[0]
    private val dummyTVShow = tvShowResponses[0]


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        cinemaRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }
    @Test
    fun getAllTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getAllTVShows()).thenReturn(dataSourceFactory)
        cinemaRepository.getAllTVShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShows()))
        verify(local).getAllTVShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getBookmarkMovies()).thenReturn(dataSourceFactory)
        cinemaRepository. getBookmarkedMovies()

        val bookmarkMovieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getBookmarkMovies()
        assertNotNull(bookmarkMovieEntities.data)
        assertEquals(movieResponses.size.toLong(), bookmarkMovieEntities.data?.size?.toLong())
    }
    @Test
    fun getDetailMovie(){
        val dummyDetailMovieEntity = MutableLiveData<MovieEntity>()
        dummyDetailMovieEntity.value = DataDummy.generateDetailMovies(DataDummy.generateDummyMovies()[0], false)
        `when`(local.getDetailMovie(dummyMovie.movieId)).thenReturn(dummyDetailMovieEntity)

        val movieEntities = LiveDataTestUtil.getValue(cinemaRepository.getDetailMovie(dummyMovie.movieId))
        verify(local).getDetailMovie(eq(dummyMovie.movieId))
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.title)
        assertEquals(movieResponses[0].title, movieEntities.data?.title)
    }

    @Test
    fun getBookmarkedTVShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getBookmarkTVShows()).thenReturn(dataSourceFactory)
        cinemaRepository. getBookmarkedTVShows()

        val bookmarkTVShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShows()))
        verify(local).getBookmarkTVShows()
        assertNotNull(bookmarkTVShowEntities.data)
        assertEquals(movieResponses.size.toLong(), bookmarkTVShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTVShow(){
        val dummyDetailTVShowEntity = MutableLiveData<TVShowEntity>()
        dummyDetailTVShowEntity.value = DataDummy.generateDetailTVShow(DataDummy.generateDummyTVShows()[0], false)
        `when`(local.getDetailTVShow(dummyTVShow.tvShowId)).thenReturn(dummyDetailTVShowEntity)

        val tvShowEntities = LiveDataTestUtil.getValue(cinemaRepository.getDetailTVShow(dummyTVShow.tvShowId))
        verify(local).getDetailTVShow(eq(dummyTVShow.tvShowId))
        assertNotNull(tvShowEntities.data)
        assertNotNull(tvShowEntities.data?.title)
        assertEquals(tvShowResponses[0].title, tvShowEntities.data?.title)
    }

}