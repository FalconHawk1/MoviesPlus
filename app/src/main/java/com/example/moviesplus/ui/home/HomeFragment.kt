package com.example.moviesplus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesplus.R
import com.example.moviesplus.adapter.MoviesAdapter
import com.example.moviesplus.adapter.MoviesHeaderAdapter
import com.example.moviesplus.adapter.TvAdapter
import com.example.moviesplus.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController
import com.example.moviesplus.adapter.MoviesTopRatedAdapter
import com.example.moviesplus.adapter.TvTopRatedAdapter

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var adapterHeader: MoviesHeaderAdapter
    private lateinit var adapterTv: TvAdapter
    private lateinit var adapterTvTopRated: TvTopRatedAdapter
    private lateinit var adapterMoviesTopRated: MoviesTopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        observerViewModel()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the click listener for the TextView using data binding
        binding.movies.setOnClickListener {
            Toast.makeText(context, "Hello, I am a TextView!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_home_to_fragment_movies)
        }

        binding.tvShows.setOnClickListener {
            Toast.makeText(context, "Hello, I am a TextView!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_home_to_fragment_tv)
        }

        binding.searchIcon.setOnClickListener {
            Toast.makeText(context, "Hello, I am a SearchView!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_home_to_fragment_search)
        }
    }

    private fun observerViewModel() {

        viewModel.getMovies()

        viewModel.getTv()

        viewModel.getTopRatedTv()

        viewModel.getTopRatedMovies()

                viewModel.movies.observe(viewLifecycleOwner) {
                it?.let {
                    adapter = MoviesAdapter(it.results){ id->
                        val bundle = Bundle()
                        bundle.putInt("id",id)
                        findNavController().navigate(R.id.action_fragment_home_to_fragment_movies_details,bundle)
                    }
                    binding.feedItemsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    binding.feedItemsList.adapter = adapter

                    adapterHeader = MoviesHeaderAdapter(it.results) { id ->
                        val bundle = Bundle()
                        bundle.putInt("id", id)
                        findNavController().navigate(R.id.action_fragment_home_to_fragment_movies_details, bundle)
                    }
                    binding.feedHeader.layoutManager = LinearLayoutManager(context)
                    binding.feedHeader.adapter = adapterHeader
                }
            }

                viewModel.tv.observe(viewLifecycleOwner) {
                    it?.let {

                        adapterTv = TvAdapter(it.results){ id->
                            Toast.makeText(requireContext(), "Mi id es: $id", Toast.LENGTH_SHORT).show()
                            val bundle = Bundle()
                            bundle.putInt("id",id)
                            findNavController().navigate(R.id.action_fragment_home_to_fragment_tv_details,bundle)
                        }
                        binding.tvItemsList.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.tvItemsList.adapter = adapterTv
                    }
                }

                viewModel.tvTopRated.observe(viewLifecycleOwner) {
                    it?.let {
                        adapterTvTopRated = TvTopRatedAdapter(it.results){ id->
                            Toast.makeText(requireContext(), "Mi id es: $id", Toast.LENGTH_SHORT).show()
                            val bundle = Bundle()
                            bundle.putInt("id",id)
                            findNavController().navigate(R.id.action_fragment_home_to_fragment_tv_details,bundle)
                        }
                        binding.tvTopRatedItemsList.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.tvTopRatedItemsList.adapter = adapterTvTopRated
                    }
                }

                viewModel.moviesTopRated.observe(viewLifecycleOwner) {
                    it?.let {
                        adapterMoviesTopRated = MoviesTopRatedAdapter(it.results){ id->
                            Toast.makeText(requireContext(), "Mi id es: $id", Toast.LENGTH_SHORT).show()
                            val bundle = Bundle()
                            bundle.putInt("id",id)
                            findNavController().navigate(R.id.action_fragment_home_to_fragment_tv_details,bundle)
                        }
                        binding.moviesTopRatedItemsList.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        binding.moviesTopRatedItemsList.adapter = adapterMoviesTopRated
                    }
                }

    }
}