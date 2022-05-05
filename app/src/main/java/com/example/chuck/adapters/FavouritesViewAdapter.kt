package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chuck.R
import com.example.chuck.holders.FavouritesViewHolder
import com.example.chuck.holders.RecyclerViewHolder
import com.example.chuck.listeners.StringClickListener
import com.example.chuck.model.Jokes
import com.example.chuck.model.Post
import com.example.chuck.util.ImgUrls
import com.example.chuck.util.WhichImage

class FavouritesViewAdapter(
    private val itemList: List<Jokes>,
    private val clickListener: StringClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_favourites,
            parent, false
        )
        return FavouritesViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavouritesViewHolder).bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}