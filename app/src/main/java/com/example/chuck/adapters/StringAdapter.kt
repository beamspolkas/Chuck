package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.CategoryViewHolder

class StringAdapter(private val mList: MutableList<String>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.string_card_view, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holderRecycler: CategoryViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holderRecycler.category.text = itemsViewModel
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
