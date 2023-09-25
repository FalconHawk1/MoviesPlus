package com.example.moviesplus.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.adapter.PopularTvShowAdapter
import com.example.moviesplus.databinding.FragmentPopularMoviesBinding
import com.example.moviesplus.databinding.FragmentPopularTvBinding
import com.example.moviesplus.ui.movies.PopularMoviesViewModel

class PopularTvFragment: Fragment() {

    lateinit var binding: FragmentPopularTvBinding
    private lateinit var adapter: PopularTvShowAdapter

    private lateinit var viewModel: PopularTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[PopularTvViewModel::class.java]

        binding = FragmentPopularTvBinding.inflate(inflater, container, false)

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

        viewModel.tv.observe(viewLifecycleOwner) {
            it?.let {
                adapter = PopularTvShowAdapter(it.results){ id->
                    Toast.makeText(requireContext(), "Mi id es: $id", Toast.LENGTH_SHORT).show()
                    //findNavController().navigate(R.id.)
                    //findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment(id))
                }

                val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

                binding.popularTvList.layoutManager = layoutManager

                binding.popularTvList.adapter = adapter
            }
        }
    }
}