package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyshelterBinding

class DailyshelterRecyclerAdapter(private val callback: (ShelterEntitiyRepo) -> Unit) :
    PagingDataAdapter<ShelterEntitiyRepo, DailyshelterRecyclerAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<ShelterEntitiyRepo>() {
            override fun areItemsTheSame(
                oldItem: ShelterEntitiyRepo,
                newItem: ShelterEntitiyRepo
            ): Boolean {
                return oldItem.shelterName == newItem.shelterName
            }

            override fun areContentsTheSame(
                oldItem: ShelterEntitiyRepo,
                newItem: ShelterEntitiyRepo
            ): Boolean {
                return oldItem.shelterName == newItem.shelterName
            }

        }
    ) {
    override fun onBindViewHolder(
        holder: DailyshelterRecyclerAdapter.ImageViewHolder,
        position: Int
    ) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyshelterRecyclerAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemDailyshelterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemDailyshelterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ShelterEntitiyRepo) {
            binding.apply {
                dailyShelterCl.setOnClickListener {
                    callback(item)
                }
                dailyShelterName.text = item.shelterName
                Glide.with(binding.root)
                    .load(R.mipmap.img_1)
                    .circleCrop()
                    .into(binding.dailyShelterIv)
            }
        }
    }
}