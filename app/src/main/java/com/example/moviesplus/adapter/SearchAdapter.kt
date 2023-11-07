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
import com.example.moviesplus.databinding.ItemTopMovieBinding
import com.example.moviesplus.model.DiscoverMoviesList

class SearchAdapter(private var list: List<DiscoverMoviesList>, val onItemClick:(itemId:Int)-> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_movie, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item.id, CommonUtils.IMAGE_URL_BASE + item.backdrop_path)
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding =ItemTopMovieBinding.bind(view)

        fun bind(itemId: Int, image: String) {
            Glide.with(itemView).load(image).transform(CenterCrop(), RoundedCorners(8)).into(binding.backdropImage)
            binding.content.setOnClickListener {
                onItemClick(itemId)
            }
            var title = ""
            for (i in list){
                if (i.id == itemId){
                    title = i.title
                }
            }
            binding.nameText.text = title
        }
    }
}