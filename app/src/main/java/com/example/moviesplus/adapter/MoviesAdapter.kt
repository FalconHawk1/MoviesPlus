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

class MoviesAdapter(internal var list: List<DiscoverMoviesList>) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poster, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(CommonUtils.IMAGE_URL_BASE + item.backdrop_path)
    }

    class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view)  {

        private val binding = ItemPosterBinding.bind(view)

        fun bind(image: String) {
            Picasso.get().load(image).into(binding.posterImage)
        }


    }
}