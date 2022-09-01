package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemRecommendAdoptBinding

class RecommendAdoptAdapter(private val callback: (AdoptPostFeed) -> Unit) :
    PagingDataAdapter<AdoptPostFeed, RecommendAdoptAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<AdoptPostFeed>() {
            override fun areItemsTheSame(
                oldItem: AdoptPostFeed,
                newItem: AdoptPostFeed
            ): Boolean {
                return oldItem.adoptionPostId == newItem.adoptionPostId
            }

            override fun areContentsTheSame(
                oldItem: AdoptPostFeed,
                newItem: AdoptPostFeed
            ): Boolean {
                return oldItem.adoptionPostId == newItem.adoptionPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: RecommendAdoptAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendAdoptAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemRecommendAdoptBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemRecommendAdoptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: AdoptPostFeed) {
            binding.recommendAdoptFeedTv1.text = item.title
            binding.recommendAdoptCl.setOnClickListener {
                callback(item)
            }
            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.recommendAdoptionIv)
        }
    }
}