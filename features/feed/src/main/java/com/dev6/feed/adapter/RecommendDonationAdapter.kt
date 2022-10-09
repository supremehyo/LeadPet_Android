package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.donate.DonationPost
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemRecommendDonationBinding

class RecommendDonationAdapter(private val callback: (DonationPost) -> Unit) :
    PagingDataAdapter<DonationPost, RecommendDonationAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DonationPost>() {
            override fun areItemsTheSame(
                oldItem: DonationPost,
                newItem: DonationPost
            ): Boolean {
                return oldItem.donationPostId == newItem.donationPostId
            }

            override fun areContentsTheSame(
                oldItem: DonationPost,
                newItem: DonationPost
            ): Boolean {
                return oldItem.donationPostId == newItem.donationPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: RecommendDonationAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendDonationAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemRecommendDonationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemRecommendDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: DonationPost) {
            binding.recommendDonationEndDate.text = "${item.startDate}~${item.endDate}"
            binding.recommendDonationFeedTv1.text = item.title
            binding.recommendDonationCl.setOnClickListener {
                callback(item)
            }
            Glide.with(binding.root)
                .load(R.mipmap.img_1)
                .centerCrop()
                .into(binding.recommendDonationIv)
        }
    }
}
