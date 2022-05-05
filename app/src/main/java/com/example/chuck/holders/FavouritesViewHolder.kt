package com.example.chuck.holders

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.chuck.R
import com.example.chuck.databinding.FragmentFavouritesBinding
import com.example.chuck.listeners.StringClickListener
import com.example.chuck.model.Jokes

class FavouritesViewHolder(private val view: View, private val clickListener: StringClickListener
) : RecyclerView.ViewHolder(view) {
    private val binding: FragmentFavouritesBinding =
        FragmentFavouritesBinding.bind(view)

    fun bind(item: Jokes) {
        binding.apply {
            favsTextView.text = item.title

//            val ranking = "Ranking: ${item.ranking}"
//            rankingText.text = ranking
//
//            externalLinkButton.setOnClickListener {
//                clickListener.invoke(GlobalConstants.EXTERNAL_LINK, item.websiteURL)
//            }
//
//            downloadButton.setOnClickListener {
//                clickListener.invoke(GlobalConstants.DOWNLOAD, item.fileURL)
//            }
//
//            mainLayout.apply {
//                val animation = AnimationUtils.loadAnimation(
//                    view.context,
//                    R.anim.zoom_in
//                )
//                startAnimation(animation)
//            }
        }

//        Glide.with(view.context)
//            .load(item.imageUrl)
//            .placeholder(R.drawable.image_placeholder)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
//            .into(binding.imageView)
    }

}