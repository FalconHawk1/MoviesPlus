package com.example.moviesplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesplus.R
import com.example.moviesplus.common.CommonUtils
import com.example.moviesplus.databinding.ItemPosterBinding
import com.example.moviesplus.model.DiscoverMoviesList
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(private var list: List<DiscoverMoviesList>, val onItemClick:(itemId:Int)-> Unit) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poster, parent, false)
        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item.id, CommonUtils.IMAGE_URL_BASE + item.backdrop_path)
    }

    inner class PopularMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPosterBinding.bind(view)

        fun bind(itemId: Int, image: String) {
            Picasso.get().load(image).into(binding.posterImage)
            binding.posterImage.setOnClickListener {
                onItemClick(itemId)
            }
        }


    }
}