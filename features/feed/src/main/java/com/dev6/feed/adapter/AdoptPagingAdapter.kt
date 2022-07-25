package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.extension.makeTimeString
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.feed.databinding.ItemAdoptBinding
import com.dev6.feed.databinding.ItemDonationBinding

class AdoptPagingAdapter(private val callback: (AdoptPostFeed) -> Unit) :
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
            binding.adoptUserId.text = item.userId
            binding.adoptTitleTv.text = item.title
            binding.adoptDateTv.text =
                makeTimeString(item.startDate[0], item.startDate[1], item.startDate[2]) +
                        "~" + makeTimeString(item.endDate[0], item.endDate[1], item.endDate[2])
            binding.itemAdoptCl.setOnClickListener {
                callback(item)
            }
        }
    }
}
