package com.abadisurio.cinematxt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abadisurio.cinematxt.data.source.local.LocalDataSource
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.source.remote.ApiResponse
import com.abadisurio.cinematxt.data.source.remote.response.MovieResponse
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource
import com.abadisurio.cinematxt.data.source.remote.response.TVShowResponse
import com.abadisurio.cinematxt.utils.AppExecutors
import com.abadisurio.cinematxt.vo.Resource

class FakeCinemaTXTRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CinemaTXTDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()
            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val course = MovieEntity(response.movieId,
                        response.title,
                        response.description,
                        response.releaseDate,
                        response.imagePath,
                        false)
                    movieList.add(course)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    if (response.movieId == movieId) {
                        val movie = MovieEntity(
                            response.movieId,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.imagePath,
                            false)

                        movieList.add(movie)
                    }
                }
                localDataSource.getDetailMovie(movieId)
            }
        }.asLiveData()
    }

    override fun setBookmarkMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setMovieBookmark(movie, state)
        }
    }

    override fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkMovies(), config).build()
    }

    override fun getAllTVShows(): LiveData<Resource<PagedList<TVShowEntity>>>{
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResponse>>(appExecutors){
            public override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<TVShowResponse>>> =
                remoteDataSource.getAllTVShows()
            public override fun saveCallResult(data: List<TVShowResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tvShow = TVShowEntity(response.tvShowId,
                        response.title,
                        response.description,
                        response.releaseDate,
                        response.imagePath,
                        false)
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTVShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTVShow(tvShowId: String): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, List<TVShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowEntity> =
                localDataSource.getDetailTVShow(tvShowId)

            override fun shouldFetch(data: TVShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<TVShowResponse>>> =
                remoteDataSource.getDetailTVShow(tvShowId)

            override fun saveCallResult(data: List<TVShowResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in data) {
                    if (response.tvShowId == tvShowId) {
                        val tvShow = TVShowEntity(
                            response.tvShowId,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.imagePath,
                            false)

                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.getDetailTVShow(tvShowId)
            }
        }.asLiveData()
    }

    override fun setBookmarkTVShow(tvShow: TVShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setTVShowBookmark(tvShow, state)
        }
    }

    override fun getBookmarkedTVShows(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkTVShows(), config).build()
    }
}