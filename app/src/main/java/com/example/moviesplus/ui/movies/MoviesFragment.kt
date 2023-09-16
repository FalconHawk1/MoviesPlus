package com.example.moviesplus.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesplus.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {

    lateinit var binding: FragmentMoviesBinding

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]

        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        observerViewModel()

        return binding.root

    }

    private fun observerViewModel() {

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner){
            it?.let {
                //ey mi lista se llena con "it" -> cada item los guarda en el adapter

            }

        }
    }
}