package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.CategoryViewHolder
import com.example.chuck.interfaces.OnListItemClicked

class StringAdapter(private val mList: MutableList<String>, private val onItemClickListener: OnListItemClicked) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.string_card_view, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holderRecycler: CategoryViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holderRecycler.category.text = itemsViewModel
        holderRecycler.itemView.setOnClickListener {
            onItemClickListener.OnClick(itemsViewModel)
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
