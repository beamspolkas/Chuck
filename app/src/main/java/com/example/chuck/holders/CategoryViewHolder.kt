package com.example.chuck.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val id: TextView = view.findViewById(R.id.id)
    val body: TextView = view.findViewById(R.id.body)
    val userId: TextView = view.findViewById(R.id.userId)
    val title: TextView = view.findViewById(R.id.title)

//    fun bindData(data: String){
//        id.text = data
//    }
}