package com.example.moviesplus.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesplus.adapter.MoviesAdapter
import com.example.moviesplus.adapter.MoviesHeaderAdapter
import com.example.moviesplus.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {

    lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var adapter2: MoviesHeaderAdapter

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

                viewModel.movies.observe(viewLifecycleOwner) {
                it?.let {
                    adapter = MoviesAdapter(it.results)
                    binding.feedItemsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    binding.feedItemsList.adapter = adapter

                    adapter2 = MoviesHeaderAdapter(it.results)
                    binding.feedHeader.layoutManager = LinearLayoutManager(context)
                    binding.feedHeader.adapter = adapter2
                }
            }
    }
}