package com.example.moviesplus.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesplus.adapter.EpisodeItemsAdapter
import com.example.moviesplus.adapter.PopularMoviesAdapter
import com.example.moviesplus.adapter.PopularTvShowAdapter
import com.example.moviesplus.databinding.FragmentMoviesDetailsBinding
import com.example.moviesplus.databinding.FragmentTvDetailsBinding
import kotlinx.coroutines.launch

class TvDetailsFragment : Fragment() {

    lateinit var binding: FragmentTvDetailsBinding
    private lateinit var adapter: PopularTvShowAdapter
    private lateinit var episodeItemsAdapter: EpisodeItemsAdapter

    private lateinit var viewModel: TvDetailsViewModel


    companion object {
        fun newInstance(tvId: Int): TvDetailsFragment {
            val fragment = TvDetailsFragment()
            val args = Bundle()
            args.putInt("id", tvId)
            fragment.arguments = args
            return fragment
        }
    }
    private val tvId: Int
        get() = requireArguments().getInt("id")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[TvDetailsViewModel::class.java]

        binding = FragmentTvDetailsBinding.inflate(inflater, container, false)

        binding.seasonPicker.setOnClickListener { handleSeasonPickerSelectClick() }

        val bundle = arguments

        val id = bundle?.getInt("id")

        if(id != null)
            viewModel.setTvId(id)
        else
            findNavController().popBackStack()

        setupUI()
        observerViewModel()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupUI() {
        episodeItemsAdapter = EpisodeItemsAdapter {}
        binding.episodesList.adapter = episodeItemsAdapter
        binding.episodesList.isNestedScrollingEnabled = false
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

        viewModel.selectedSeasonDetails.observe(viewLifecycleOwner) {
            it?.let {
                episodeItemsAdapter.submitList(it.data?.episodes)
            }
        }

        viewModel.selectedSeasonNameIndexPair.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.selectedSeasonText.text = it.first
            }
        }
    }

    private fun handleSeasonPickerSelectClick() {
        Toast.makeText(context, "hola", Toast.LENGTH_SHORT).show()
        val details = viewModel.tvDetails.value
        if (details != null) {
            val seasonNames =
                details.seasons.mapIndexed { _, season -> season.name } as ArrayList<String>
            val itemPickerFragment: ItemPickerFragment =
                ItemPickerFragment.newInstance(seasonNames,
                    viewModel.selectedSeasonNameIndexPair.value?.second!!)
            itemPickerFragment.showsDialog = true
            itemPickerFragment.show(parentFragmentManager, "pickerDialog")
            itemPickerFragment.setItemClickListener { newSelectedPosition ->
                val selectedSeason = details.seasons[newSelectedPosition]
                viewModel.selectedSeasonNameIndexPair.value =
                    Pair(selectedSeason.name, newSelectedPosition)
                lifecycleScope.launch {
                    viewModel.fetchSeasonDetails(tvId!!, selectedSeason.seasonNumber)
                }
            }
        }
    }
}