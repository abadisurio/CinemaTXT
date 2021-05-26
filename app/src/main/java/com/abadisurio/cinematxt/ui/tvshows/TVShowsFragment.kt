package com.abadisurio.cinematxt.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadisurio.cinematxt.databinding.FragmentTVShowsBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory
import com.abadisurio.cinematxt.vo.Status

class TVShowsFragment : Fragment() {

    private lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowsViewModel::class.java]

            val tvshowsAdapter = TVShowsAdapter()

            viewModel.getTVShows().observe(viewLifecycleOwner, {tvShows ->
                if(tvShows != null){
                    when(tvShows.status){
                        Status.LOADING -> fragmentTVShowsBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTVShowsBinding.progressBar.visibility = View.GONE
                            tvshowsAdapter.setTVShows(tvShows.data)
                            tvshowsAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTVShowsBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTVShowsBinding.rvTvshows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowsAdapter
            }
        }
    }
}
