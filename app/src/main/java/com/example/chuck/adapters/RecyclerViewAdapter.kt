package com.example.chuck.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.holders.ViewHolder
import com.example.chuck.model.Post

class RecyclerViewAdapter(private val mList: MutableList<Post>) : RecyclerView.Adapter<ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.body.text = itemsViewModel.body
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")//dodane
    fun setData(body: MutableList<Post>) {
        mList.clear()
        mList.addAll(body)
        notifyDataSetChanged()
    }
}