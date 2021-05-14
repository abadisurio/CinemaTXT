package com.abadisurio.cinematxt.data.source

import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.data.source.remote.response.RemoteDataSource

class CinemaTXTRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CinemaTXTDataSource {

    companion object {
        @Volatile
        private var instance: CinemaTXTRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CinemaTXTRepository =
            instance ?: synchronized(this) {
                instance ?: CinemaTXTRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()
        for (response in movieResponses) {
            val movie = MovieEntity(response.movieId,
                response.title,
                response.description,
                response.releaseDate,
                response.imagePath)
            movieList.add(movie)
        }
        return movieList
    }

    override fun getDetailMovie(movieId: String): MovieEntity {
        lateinit var movie: MovieEntity
        val movieResponses = remoteDataSource.getAllMovies()
        for (response in movieResponses) {
            if (response.movieId == movieId) {
                movie = MovieEntity(response.movieId,
                    response.title,
                    response.description,
                    response.releaseDate,
                    response.imagePath)
            }
        }
        return movie
    }

    override fun getBookmarkedMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()
        for (response in movieResponses) {
            val movie = MovieEntity(response.movieId,
                response.title,
                response.description,
                response.releaseDate,
                response.imagePath)
            movieList.add(movie)
        }
        return movieList
    }

    override fun getDetailTVShow(tvShowId: String): TVShowEntity {
        val tvShowResponse = remoteDataSource.getAllTVShows()
        lateinit var tvShow: TVShowEntity
        for (response in tvShowResponse) {
            if (response.tvShowId == tvShowId) {
                tvShow = TVShowEntity(response.tvShowId,
                    response.title,
                    response.description,
                    response.releaseDate,
                    response.imagePath)
            }
        }
        return tvShow
    }

    override fun getAllTVShows(): List<TVShowEntity> {
        val tvShowResponses = remoteDataSource.getAllTVShows()
        val tvShowList = ArrayList<TVShowEntity>()
        for (response in tvShowResponses) {
            val tvShow = TVShowEntity(response.tvShowId,
                response.title,
                response.description,
                response.releaseDate,
                response.imagePath)
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    override fun getBookmarkedTVShows(): List<TVShowEntity> {
        val tvShowResponses = remoteDataSource.getAllTVShows()
        val tvShowList = ArrayList<TVShowEntity>()
        for (response in tvShowResponses) {
            val tvShow = TVShowEntity(response.tvShowId,
                response.title,
                response.description,
                response.releaseDate,
                response.imagePath)
            tvShowList.add(tvShow)
        }
        return tvShowList
    }
}