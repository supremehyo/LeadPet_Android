package com.dev6.feed.adapter.Saved
import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.save.SavedDonationData
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemSavedDonationBinding

class SavedDonationAdapter (private val callback: (SavedDonationData) -> Unit) :
    PagingDataAdapter<SavedDonationData, SavedDonationAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<SavedDonationData>() {
            override fun areItemsTheSame(
                oldItem: SavedDonationData,
                newItem: SavedDonationData
            ): Boolean {
                return oldItem.donationPostId == newItem.donationPostId
            }

            override fun areContentsTheSame(
                oldItem: SavedDonationData,
                newItem: SavedDonationData
            ): Boolean {
                return oldItem.donationPostId == newItem.donationPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: SavedDonationAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedDonationAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemSavedDonationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemSavedDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: SavedDonationData) {

            binding.savedDonationCl.setOnClickListener {
                callback(item)
            }
            binding.savedDonationTitle.text = item.title
            binding.savedDonationUserId.text = item.userId

            Glide.with(binding.root)
                .load(Uri.parse(item.images?.get(0) ?: ""))
                .error(R.mipmap.img_1)
                .into(binding.savedDonationIv)
        }
    }
}