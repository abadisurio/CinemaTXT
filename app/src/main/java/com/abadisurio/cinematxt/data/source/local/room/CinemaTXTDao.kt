package com.abadisurio.cinematxt.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity


@Dao
interface CinemaTXTDao {

    //movies
    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getBookmarkMovies(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getDetailMovie(id: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movies: MovieEntity)

    //tv show
    @Query("SELECT * FROM tvshowentities")
    fun getTVShows(): LiveData<List<TVShowEntity>>

    @Query("SELECT * FROM tvshowentities WHERE favorited = 1")
    fun getBookmarkTVShows(): LiveData<List<TVShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getDetailTVShow(id: String): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tv: List<TVShowEntity>)

    @Update
    fun updateTVShow(tv: TVShowEntity)
}