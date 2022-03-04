package com.example.chuck.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView

    init {
        // Define click listener for the ViewHolder's View.
        textView = view.findViewById<TextView>(R.id.textView)
    }

    fun bindData(data: String){
        textView.text = data
    }
}