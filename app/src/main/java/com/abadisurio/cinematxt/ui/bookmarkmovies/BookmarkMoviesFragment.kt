package com.abadisurio.cinematxt.ui.bookmarkmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadisurio.cinematxt.databinding.FragmentMoviesBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory

class BookmarkMoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private lateinit var moviesAdapter: BookmarkMoviesAdapter
    private lateinit var viewModel: BookmarkMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkMoviesViewModel::class.java]

            moviesAdapter = BookmarkMoviesAdapter()

            viewModel.getBookmarkMovies().observe(viewLifecycleOwner, {movies ->
                if(movies != null){
                    fragmentMoviesBinding.progressBar.visibility = View.GONE
                    moviesAdapter.setMovies(movies)
                    moviesAdapter.submitList(movies)
                    moviesAdapter.notifyDataSetChanged()
                }
            })

            with(fragmentMoviesBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}
