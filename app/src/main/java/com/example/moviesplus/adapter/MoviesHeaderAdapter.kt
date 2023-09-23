package com.example.moviesplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesplus.common.CommonUtils
import com.example.moviesplus.databinding.ItemFeedHeaderBinding
import com.example.moviesplus.databinding.ItemMediaBinding
import com.example.moviesplus.model.DiscoverMoviesList
import com.squareup.picasso.Picasso

class MoviesHeaderAdapter(internal val list: List<DiscoverMoviesList>) :
    RecyclerView.Adapter<MoviesHeaderAdapter.MoviesHeaderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.moviesplus.R.layout.item_feed_header, parent, false)
        return MoviesHeaderViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MoviesHeaderViewHolder,
        position: Int
    ) {
        val item = list[0]
        holder.bind(CommonUtils.IMAGE_URL_BASE + item.backdrop_path)
    }

    override fun getItemCount(): Int = 1

    class MoviesHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemFeedHeaderBinding.bind(view)

        fun bind(image: String) {
            Picasso.get().load(image).into(binding.backgroundImage)
        }

    }


}