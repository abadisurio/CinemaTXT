package com.abadisurio.cinematxt.ui.detail

import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.MovieEntity
import com.abadisurio.cinematxt.data.TVShowEntity
import com.abadisurio.cinematxt.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var showId: String

    fun setSelectedShow(showId: String) {
        this.showId = showId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.movieId == showId) {
                movie = movieEntity
            }
        }
        return movie
    }
    fun getTVShow(): TVShowEntity {
        lateinit var tvShow: TVShowEntity
        val tvShowEntities = DataDummy.generateDummyTVShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == showId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}