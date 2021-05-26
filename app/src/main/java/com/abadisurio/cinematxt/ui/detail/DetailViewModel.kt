package com.abadisurio.cinematxt.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abadisurio.cinematxt.data.source.local.entity.MovieEntity
import com.abadisurio.cinematxt.data.source.local.entity.TVShowEntity
import com.abadisurio.cinematxt.data.CinemaTXTRepository
import com.abadisurio.cinematxt.vo.Resource

class DetailViewModel(private val cinemaTXTRepository: CinemaTXTRepository): ViewModel() {
//    private lateinit var movieId: String
    val movieId = MutableLiveData<String>()
//    private lateinit var tvShowId: String
    val tvShowId = MutableLiveData<String>()

    fun setSelectedMovie(showId: String) {
        this.movieId.value = showId
    }
    fun setSelectedTVShow(showId: String) {
        this.tvShowId.value = showId
    }

    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){movieId->
        cinemaTXTRepository.getDetailMovie(movieId)
    }
    var detailTVShow: LiveData<Resource<TVShowEntity>> = Transformations.switchMap(movieId){tvShowId->
        cinemaTXTRepository.getDetailTVShow(tvShowId)
    }

    fun setBookmarkMovie(){
        val movieResource = detailMovie.value
        if (movieResource != null) {
            val movie = movieResource.data

            val newState = !movie?.favorited!!
            cinemaTXTRepository.setBookmarkMovie(movie, newState)
        }
    }
    fun setBookmarkTVShow(){
        val tvShowResource = detailTVShow.value
        if (tvShowResource != null) {
            val tvShow = tvShowResource.data

            val newState = !tvShow?.favorited!!
            cinemaTXTRepository.setBookmarkTVShow(tvShow, newState)
        }
    }

}