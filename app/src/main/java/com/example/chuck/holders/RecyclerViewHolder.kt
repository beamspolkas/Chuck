package com.example.chuck.holders

import android.view.View
//import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //val iconUrl: TextView = view.findViewById(R.id.icon_url)
    val id: TextView = view.findViewById(R.id.id)
    //val url: TextView = view.findViewById(R.id.url)
    val value: TextView = view.findViewById(R.id.value)
    //val image: ImageView = view.findViewById(R.id.imageView)
    //val category: TextView = view.findViewById(R.id.category)
}