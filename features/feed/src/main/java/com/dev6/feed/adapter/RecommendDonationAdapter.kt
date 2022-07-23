package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyshelterBinding
import com.dev6.feed.databinding.ItemDonationBinding
import com.dev6.feed.databinding.ItemRecommendDonationBinding

class RecommendDonationAdapter(private val callback: (DonationPostFeed) -> Unit) :
    PagingDataAdapter<DonationPostFeed, RecommendDonationAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DonationPostFeed>() {
            override fun areItemsTheSame(
                oldItem: DonationPostFeed,
                newItem: DonationPostFeed
            ): Boolean {
                return oldItem.donationPostId == newItem.donationPostId
            }

            override fun areContentsTheSame(
                oldItem: DonationPostFeed,
                newItem: DonationPostFeed
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
        fun onBind(item: DonationPostFeed) {
            binding.recommendDonationEndDate.text = "까지"
            binding.recommendDonationFeedTv1.text = item.title
            binding.recommendDonationCl.setOnClickListener {
                callback(item)
            }
            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.recommendDonationIv)
        }
    }
}
