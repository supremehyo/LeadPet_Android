package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyfeedBinding


class DailyPagingAdapter(private val callback: (DailyPostFeed) -> Unit):
    PagingDataAdapter<DailyPostFeed, DailyPagingAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DailyPostFeed>() {
            override fun areItemsTheSame(oldItem: DailyPostFeed, newItem: DailyPostFeed): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(oldItem: DailyPostFeed, newItem: DailyPostFeed): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: DailyPagingAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyPagingAdapter.ImageViewHolder {
        return ImageViewHolder(binding = ItemDailyfeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ImageViewHolder(private val binding: ItemDailyfeedBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: DailyPostFeed) {
            binding.dailyFeedContentTv.text = item.contents
            binding.dailyFeedTitleTv.text = item.title
            binding.dailyFeedProfileTv.text = item.userId
            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.dailyFeedIv)

            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.dailyFeedProfileIv)

            binding.constraintLayout2.setOnClickListener {
                callback(item)
            }
        }
    }
}
