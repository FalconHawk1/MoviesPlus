package com.example.moviesplus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesplus.R
import com.example.moviesplus.model.DiscoverMoviesList
import com.example.moviesplus.model.MoviesResponse

class MoviesAdapter(internal var context: Context, internal var list: List<DiscoverMoviesList>) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = list!![position]
        //holder.bind(item.toString())
    }

    class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view)  {

        fun onCreate(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_media, viewGroup, false)
            return MoviesViewHolder(view)
        }


    }
}