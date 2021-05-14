package com.abadisurio.cinematxt.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abadisurio.cinematxt.data.source.CinemaTXTRepository
import com.abadisurio.cinematxt.ui.movies.MoviesViewModel
import com.abadisurio.cinematxt.di.Injection
import com.abadisurio.cinematxt.ui.detail.DetailViewModel
import com.abadisurio.cinematxt.ui.tvshows.TVShowsViewModel

class ViewModelFactory private constructor(private val mShowRepository: CinemaTXTRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mShowRepository) as T
            }
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> {
                return TVShowsViewModel(mShowRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mShowRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}