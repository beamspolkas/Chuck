package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chuck.R
import com.example.chuck.holders.RecyclerViewHolder
import com.example.chuck.model.Post
import com.example.chuck.util.ImgUrls
import com.example.chuck.util.WhichImage
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private val mList: MutableList<Post>,
                          private val context: Context,
                          private val imgUrls: ArrayList<String>,
                          private val whichImage: WhichImage) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holderRecycler: RecyclerViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        if(whichImage == WhichImage.RANDOM) {
            val randomImage = (0 until imgUrls.size - 1).random()
            Glide
                .with(context)
                .load(imgUrls[randomImage])
                .override(200, 200)
                .into(holderRecycler.image)
        } else {
            for(element in ImgUrls.hashmap){
                if(element.key == itemsViewModel.categories[0]){
                    Glide
                        .with(holderRecycler.itemView)
                        .load(element.value)
                        .override(200, 200)
                        .into(holderRecycler.image)
                }
            }
        }
        holderRecycler.value.text = itemsViewModel.value
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(body: MutableList<Post>) {
        mList.clear()
        mList.addAll(body)
        notifyDataSetChanged()
    }
}
