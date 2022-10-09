package com.dev6.feed.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.util.extension.makeProceedingString
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemAdoptBinding

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
            binding.adoptBreedTv.text = item.animalType.name+"+"+item.species+"+"+item.gender
            binding.adoptDateTv.text = "" //여기도 다 null인 상태라 비워뒀음.
           // binding.announceTv.text = makeProceedingString(item.endDate ?: listOf("2022","10","5","12","00","00"))
            binding.adoptAgeTv.text = item.age.toString()+"년 출생"
            binding.adoptDiseaseTv.text = item.disease
            Glide.with(binding.root)
                .load("")
                .error(R.mipmap.img_2)
                .centerCrop()
                .into(binding.imageView4)
            binding.itemAdoptCl.setOnClickListener {
                callback(item)
            }
        }
    }
}
