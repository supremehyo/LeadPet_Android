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
import com.dev6.feed.databinding.ItemDonationBinding

class DonationPagingAdapter(private val callback: (DonationPost) -> Unit) :
    PagingDataAdapter<DonationPost, DonationPagingAdapter.ImageViewHolder>(
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
    override fun onBindViewHolder(holder: DonationPagingAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonationPagingAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemDonationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: DonationPost) {
            binding.donationShelterNameTv.text = item.userId
            binding.recommentdonationTv.text = item.title
            binding.textView5.text = item.contents
            binding.donationTypeTv.text = item.donationMethod ?: "사료"
            binding.donationEndTimeTv.text =
                item.endDate[0] + "." + item.endDate[1] + "." + item.endDate[2] + "까지"

            binding.itemDonationCl.setOnClickListener {
                callback(item)
            }
            Glide.with(binding.root)
                .load(Uri.parse(""))
                .error(R.drawable.dailay_image1)
                .circleCrop()
                .into(binding.donationFeedIv)
        }
    }
}
