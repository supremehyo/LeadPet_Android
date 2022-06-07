package com.dev6.feed.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyfeedBinding
import com.dev6.feed.databinding.ItemDailyshelterBinding



class PagingAdapter:
    PagingDataAdapter<DailyFeedEntitiy, PagingAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DailyFeedEntitiy>() {
            override fun areItemsTheSame(oldItem: DailyFeedEntitiy, newItem: DailyFeedEntitiy): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(oldItem: DailyFeedEntitiy, newItem: DailyFeedEntitiy): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: PagingAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position)?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagingAdapter.ImageViewHolder {
        return ImageViewHolder(binding = ItemDailyfeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ImageViewHolder(private val binding: ItemDailyfeedBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: DailyFeedEntitiy) {
                Glide.with(binding.root)
                    .load(Uri.parse(item.images[0]))
                    .circleCrop()
                    .error(R.drawable.dailay_image1)
                    .into(binding.dailyFeedProfileIv)

            binding.dailyFeedContentTv.text = item.contents

        }
    }
}
