package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.ViewHolder
import com.example.chuck.model.Post

class RecyclerViewAdapter(private val mList: MutableList<Post>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.iconUrl.text = itemsViewModel.icon_url
        holder.id.text = itemsViewModel.id
        holder.url.text = itemsViewModel.url
        holder.value.text = itemsViewModel.value
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
