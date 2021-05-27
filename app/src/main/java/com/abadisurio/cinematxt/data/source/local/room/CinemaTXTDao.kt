package com.abadisurio.cinematxt.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity


@Dao
interface CinemaTXTDao {

    //movies
    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getBookmarkMovies(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getDetailMovie(id: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movies: MovieEntity)

    //tv show
    @Query("SELECT * FROM tvshowentities")
    fun getTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE favorited = 1")
    fun getBookmarkTVShows(): DataSource.Factory<Int, TVShowEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getDetailTVShow(id: String): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tv: List<TVShowEntity>)

    @Update
    fun updateTVShow(tv: TVShowEntity)
}