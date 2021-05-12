package com.abadisurio.cinematxt.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadisurio.cinematxt.databinding.FragmentTVShowsBinding

class TVShowsFragment : Fragment() {

    private lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TVShowsViewModel::class.java]
            val tvshows = viewModel.getTVShows()

            val tvshowsAdapter = TVShowsAdapter()
            tvshowsAdapter.setTVShows(tvshows)
            with(fragmentTVShowsBinding.rvTvshows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
//                Log.d("movie: ", tvshowsAdapter.toString())
                adapter = tvshowsAdapter
            }
        }
    }
}
