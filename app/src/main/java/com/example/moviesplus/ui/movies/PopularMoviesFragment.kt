package com.example.moviesplus.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviesplus.adapter.MoviesAdapter
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.databinding.FragmentPopularMoviesBinding

class PopularMoviesFragment : Fragment() {

    lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var adapter: PopularMoviesAdapter

    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[PopularMoviesViewModel::class.java]

        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)

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
                adapter = PopularMoviesAdapter(it.results){ id->
                    Toast.makeText(requireContext(), "Mi id es: $id", Toast.LENGTH_SHORT).show()
                    //findNavController().navigate(R.id.)
                    //findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment(id))
                }

                val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

                binding.popularMoviesList.layoutManager = layoutManager

                binding.popularMoviesList.adapter = adapter
            }
        }
    }
}