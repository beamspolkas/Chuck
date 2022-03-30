package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.RecyclerViewHolder
import com.example.chuck.model.Post

class RecyclerViewAdapter(private val mList: MutableList<Post>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holderRecycler: RecyclerViewHolder, position: Int) {
        val itemsViewModel = mList[position]
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
