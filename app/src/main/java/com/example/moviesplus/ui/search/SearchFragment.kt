package com.example.moviesplus.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviesplus.R
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.adapter.SearchAdapter
import com.example.moviesplus.databinding.FragmentPopularMoviesBinding
import com.example.moviesplus.databinding.FragmentSearchBinding
import com.example.moviesplus.ui.movies.PopularMoviesViewModel

class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchResultsViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[SearchResultsViewModel::class.java]

        binding = FragmentSearchBinding.inflate(inflater, container, false)

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

        viewModel.getPopularMovies()

        viewModel.movies.observe(viewLifecycleOwner) {
            it?.let {
                adapter = SearchAdapter(it.results){ id->
                    val bundle = Bundle()
                    bundle.putInt("id",id)
                    findNavController().navigate(R.id.action_fragment_search_to_fragment_movies_details,bundle)
                }

                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

                binding.topSearchesList.layoutManager = layoutManager

                binding.topSearchesList.adapter = adapter
            }
        }
    }
}