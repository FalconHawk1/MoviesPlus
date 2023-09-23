package com.example.moviesplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesplus.R
import com.example.moviesplus.common.CommonUtils
import com.example.moviesplus.databinding.ItemPosterBinding
import com.example.moviesplus.model.DiscoverMoviesList
import com.example.moviesplus.model.DiscoverTvList
import com.squareup.picasso.Picasso

class TvAdapter(internal var list: List<DiscoverTvList>) :
    RecyclerView.Adapter<TvAdapter.TvListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poster, parent, false)
        return TvListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(CommonUtils.IMAGE_URL_BASE + item.backdropPath)
    }

    class TvListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPosterBinding.bind(view)

        fun bind(image: String) {
            Picasso.get().load(image).into(binding.posterImage)
        }


    }
}