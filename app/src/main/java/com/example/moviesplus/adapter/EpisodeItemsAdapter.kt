package com.example.moviesplus.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.moviesplus.common.CommonUtils
import com.example.moviesplus.model.Episode
import com.example.moviesplus.databinding.ItemEpisodeBinding
import com.netflixclone.extensions.hide

class EpisodeItemsAdapter(private val onItemClick: ((Episode) -> Unit)) :
    ListAdapter<Episode, EpisodeItemViewHolder>(EpisodeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EpisodeItemViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(episode, position, CommonUtils.IMAGE_URL_BASE + episode.stillPath)
    }
}

class EpisodeItemViewHolder(
    private val binding: ItemEpisodeBinding,
    private val onItemClick: ((Episode) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(episode: Episode, position: Int, image: String) {
        val title =  "${position+1}. ${episode.name}"
        Glide.with(binding.stillImage).load(image).transform(CenterCrop())
            .into(binding.stillImage)
        binding.stillImage.clipToOutline = true
        binding.nameText.text = title
        binding.ratingText.text = episode.voteAverage.toString()
        if (episode.overview.isNotBlank()) {
            binding.overviewText.text = episode.overview
        } else {
            binding.overviewText.hide()
        }
        binding.root.setOnClickListener { onItemClick(episode) }
    }
}

class EpisodeItemDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}