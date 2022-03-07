package com.example.chuck.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val body: TextView
    val userId: TextView
    val title: TextView

    init {
        // Define click listener for the ViewHolder's View.
        textView = view.findViewById(R.id.textView)
        body = view.findViewById(R.id.body)
        userId = view.findViewById(R.id.userId)
        title = view.findViewById(R.id.title)
    }

    fun bindData(data: String){
        textView.text = data
    }
}