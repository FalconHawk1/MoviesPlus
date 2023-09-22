package com.example.moviesplus.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesplus.adapter.MoviesAdapter
import com.example.moviesplus.databinding.FragmentMoviesBinding
import com.example.moviesplus.model.DiscoverMoviesList

class MoviesFragment: Fragment() {

    lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        observerViewModel()

        return binding.root

    }

    private fun observerViewModel() {

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner){
            it?.let {
                //ey mi lista se llena con "it" -> cada item los guarda en el adapter

                adapter = MoviesAdapter(it.results)
                binding.feedItemsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                binding.feedItemsList.adapter = adapter

            }

        }
    }
}