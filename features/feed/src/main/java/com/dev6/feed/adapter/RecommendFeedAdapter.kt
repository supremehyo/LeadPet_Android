package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyfeedBinding
import com.dev6.feed.databinding.ItemDailyshelterBinding
import com.dev6.feed.databinding.ItemRecommendFeedBinding

class RecommendFeedAdapter(private val callback: (DailyPostFeed) -> Unit):
    PagingDataAdapter<DailyPostFeed, RecommendFeedAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DailyPostFeed>() {
            override fun areItemsTheSame(oldItem: DailyPostFeed, newItem: DailyPostFeed): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(oldItem: DailyPostFeed, newItem: DailyPostFeed): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: RecommendFeedAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendFeedAdapter.ImageViewHolder {
        return ImageViewHolder(binding = ItemRecommendFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ImageViewHolder(private val binding: ItemRecommendFeedBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: DailyPostFeed) {
            binding.recommendFeeTv1.text = item.title
            binding.recommendFeedTv2.text = item.userId
            binding.recommendItemCl.setOnClickListener {
                callback(item)
            }
            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.recommendFeedProfileIv)
        }
    }
}
