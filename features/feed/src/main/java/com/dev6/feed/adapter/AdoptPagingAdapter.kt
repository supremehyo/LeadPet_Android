package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.feed.databinding.ItemAdoptBinding
import com.dev6.feed.databinding.ItemDonationBinding

class AdoptPagingAdapter :
    PagingDataAdapter<AdoptPostFeed, AdoptPagingAdapter.ImageViewHolder>(
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
    override fun onBindViewHolder(holder: AdoptPagingAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
        Log.v("Agasdggaase", item.contents)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdoptPagingAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemAdoptBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemAdoptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: AdoptPostFeed) {
            Log.v("asdfsadf" , item.userId)
            binding.adoptUserId.text = item.userId
            binding.adoptTitleTv.text = item.title
        }
    }
}
