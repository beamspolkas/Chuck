package com.example.chuck.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.CategoryViewHolder
import com.example.chuck.model.Post

class RecyclerCategoriesViewAdapter(private val mList: MutableList<Post>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_category, parent, false)

        return CategoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holderCategory: CategoryViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holderCategory.body.text = "Body: " + itemsViewModel.body
        holderCategory.userId.text = "User ID: " + itemsViewModel.userId.toString()
        holderCategory.id.text = "ID: " + itemsViewModel.id.toString()
        holderCategory.title.text = "Title: " + itemsViewModel.title
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