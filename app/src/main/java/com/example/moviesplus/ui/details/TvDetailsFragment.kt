package com.example.moviesplus.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.adapter.PopularTvShowAdapter
import com.example.moviesplus.databinding.FragmentMoviesDetailsBinding
import com.example.moviesplus.databinding.FragmentTvDetailsBinding

class TvDetailsFragment : Fragment() {

    lateinit var binding: FragmentTvDetailsBinding
    private lateinit var adapter: PopularTvShowAdapter

    private lateinit var viewModel: TvDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[TvDetailsViewModel::class.java]

        binding = FragmentTvDetailsBinding.inflate(inflater, container, false)

        val bundle = arguments

        val id = bundle?.getInt("id")

        if(id != null)
            viewModel.setTvId(id)
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

        viewModel.tvDetails.observe(viewLifecycleOwner){
            it?.let {
                binding.header.titleText.text = it.name
                binding.header.yearText.text = it.firstAirDate
                binding.header.overviewText.text = it.overview
                binding.header.ratingText.text = it.voteAverage.toString()
                binding.header.runtimeText.text = it.episodeRunTime.toString()
            }
        }

        viewModel.tvId.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.getTvDetails()
            }
        }
    }
}