package com.example.chuck.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val id: TextView = view.findViewById(R.id.id)
    val value: TextView = view.findViewById(R.id.value)
    val image: ImageView = view.findViewById(R.id.imageView)
}