package com.example.moviesplus.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moviesplus.R
import com.example.moviesplus.common.CommonUtils
import com.example.moviesplus.databinding.ItemPosterBinding
import com.example.moviesplus.model.DiscoverTvList

class TvTopRatedAdapter(private var list: List<DiscoverTvList>, val onItemClick:(itemId:Int)-> Unit) :
    RecyclerView.Adapter<TvTopRatedAdapter.TvTopRatedViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvTopRatedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_media, parent, false)
        return TvTopRatedViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: TvTopRatedViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item.id,CommonUtils.IMAGE_URL_BASE + item.posterPath)
    }

    inner class TvTopRatedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPosterBinding.bind(view)

        fun bind(itemId: Int, image: String) {
            Glide.with(itemView).load(image).transform(CenterCrop(), RoundedCorners(8)).into(binding.posterImage)
            binding.posterImage.setOnClickListener {
                onItemClick(itemId)
            }
        }


    }
}