package com.example.chuck.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val categories: TextView = view.findViewById(R.id.categories)
}