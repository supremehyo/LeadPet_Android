package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.daily.DailyPost
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemRecommendFeedBinding

class RecommendFeedAdapter(private val callback: (DailyPost) -> Unit) :
    PagingDataAdapter<DailyPost, RecommendFeedAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DailyPost>() {
            override fun areItemsTheSame(oldItem: DailyPost, newItem: DailyPost): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(oldItem: DailyPost, newItem: DailyPost): Boolean {
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
        return ImageViewHolder(
            binding = ItemRecommendFeedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemRecommendFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: DailyPost) {
            binding.recommendFeeTv1.text = item.title
            binding.writer = item.userId
            binding.recommendItemCl.setOnClickListener {
                callback(item)
            }

            Glide.with(binding.root)
                .load(R.mipmap.img_2)
                .centerCrop()
                .into(binding.recommentFeedIv)

        }
    }
}
