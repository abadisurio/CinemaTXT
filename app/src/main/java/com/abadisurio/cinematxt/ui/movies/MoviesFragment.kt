package com.abadisurio.cinematxt.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadisurio.cinematxt.databinding.FragmentMoviesBinding
import com.abadisurio.cinematxt.viewmodel.ViewModelFactory
import com.abadisurio.cinematxt.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            Log.d("punya siapa",( requireActivity()::class.simpleName ).toString())
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

//            val selectedViewModel = if(requireActivity()::class.simpleName == "BookmarkActivity"){
//                return viewModel.
//            }

            Log.d("udah selesai", "moviefragmnet")
            viewModel.getMovies().observe(viewLifecycleOwner, {movies ->
                if(movies != null){
                    when(movies.status){
                        Status.LOADING -> fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            moviesAdapter.setMovies(movies.data)
                            moviesAdapter.notifyDataSetChanged()
                            moviesAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            fragmentMoviesBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
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
