package com.example.moviesplus.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.databinding.FragmentMoviesDetailsBinding

class MoviesDetailsFragment  : Fragment() {

    lateinit var binding: FragmentMoviesDetailsBinding
    private lateinit var adapter: PopularMoviesAdapter

    private lateinit var viewModel: MoviesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[MoviesDetailsViewModel::class.java]

        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        
        val bundle = arguments
        
        val id = bundle?.getInt("id")

        if(id != null)
            viewModel.setMovieId(id)
        else
            findNavController().popBackStack()

        observerViewModel()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observerViewModel() {

        viewModel.moviesDetails.observe(viewLifecycleOwner){
            it?.let {
                binding.header.titleText.text = it.title
                binding.header.yearText.text = it.releaseDate
                binding.header.overviewText.text = it.overview
                binding.header.ratingText.text = it.voteAverage.toString()
                binding.header.runtimeText.text = it.runtime.toString()
            }
        }

        viewModel.movieId.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.getMoviesDetails()
            }
        }
    }
}