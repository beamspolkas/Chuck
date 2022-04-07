package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chuck.R
import com.example.chuck.fragments.CategoriesFragment
import com.example.chuck.holders.CategoryViewHolder
import com.example.chuck.interfaces.OnListItemClicked
import com.example.chuck.util.ImgUrls.Companion.hashmap

class StringAdapter(private val mList: MutableList<String>, private val onItemClickListener: OnListItemClicked) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.string_card_view, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holderRecycler: CategoryViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holderRecycler.categories.text = itemsViewModel

        for(element in hashmap){
            if(element.key == itemsViewModel){
                Glide
                    .with(holderRecycler.itemView)
                    .load(element.value)
                    .override(200, 200)
                    .into(holderRecycler.image)
            }
        }

        holderRecycler.itemView.setOnClickListener {
            onItemClickListener.onClick(itemsViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStringData(body: MutableList<String>) {
        mList.clear()
        mList.addAll(body)
        notifyDataSetChanged()
    }
}
