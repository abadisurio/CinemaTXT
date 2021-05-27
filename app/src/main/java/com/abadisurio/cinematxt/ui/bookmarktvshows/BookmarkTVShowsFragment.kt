package com.abadisurio.cinematxt.ui.bookmarktvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadisurio.cinematxt.databinding.FragmentTVShowsBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory

class BookmarkTVShowsFragment : Fragment() {

    private lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding
    private lateinit var tvshowsAdapter: BookmarkTVShowsAdapter
    private lateinit var viewModel: BookmarkTVShowsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkTVShowsViewModel::class.java]

            tvshowsAdapter = BookmarkTVShowsAdapter()

            viewModel.getBookmarkTVShows().observe(viewLifecycleOwner, {tvShows ->
                if(tvShows != null){
                    fragmentTVShowsBinding.progressBar.visibility = View.GONE
                    tvshowsAdapter.setTVShows(tvShows)
                    tvshowsAdapter.notifyDataSetChanged()
                    tvshowsAdapter.submitList(tvShows)
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
